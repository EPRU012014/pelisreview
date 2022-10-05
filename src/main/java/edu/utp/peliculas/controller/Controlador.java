package edu.utp.peliculas.controller;


import edu.utp.peliculas.model.*;
import edu.utp.peliculas.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController
@Slf4j
@RequestMapping("/admin")
public class Controlador {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private GeneroService  generoService;
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private OpinionService opinionService;
    @Autowired
    private RolService rolService;
    @Autowired
    private ClasificacionService clasificacionService;



    //Listar Usuarios,Generos,Peliculas,Categorias,Opinion

    @GetMapping("/usuario")
    public String listarUsuarios(Model model){
        ArrayList<Usuario> usuarios=usuarioService.listarUsuarios();
        model.addAttribute("usuarios",usuarios);
        return "admin/usuario/listar";
    }

    @GetMapping("/genero")
    public String listarGeneros(Model model){
        List<Genero> generos=generoService.listarGeneros();
        model.addAttribute("generos",generos);
        return "admin/genero/listar";
    }

    @GetMapping("/pelicula")
    public String listarPeliculas(Model model){
        List<Pelicula> peliculas=peliculaService.listarPeliculas();
        model.addAttribute("peliculas",peliculas);
        return "admin/pelicula/listar";
    }

    @GetMapping("/opinion/{id}")
    public String listarOpiniones(@PathVariable("id") Long id,Model model){
        List<Opinion> opiniones=opinionService.findbyPeliculaId(id);
        //log.info(opiniones.toString());
        model.addAttribute("opiniones",opiniones);
        return "admin/opinion/listar";
    }

    //Eliminar Usuarios,Generos,Peliculas,Categorias.
    @GetMapping("/usuario/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id){
        List<Opinion> opiniones=opinionService.findbyUsuarioId(id);
        for(Opinion obj:opiniones){
            opinionService.eliminar(obj.getId());
        }
        usuarioService.eliminar(id);
        return "redirect:/admin/usuario/listar";
    }

    @GetMapping("/genero/eliminar/{id}")
    public String eliminarGenero(@PathVariable("id") Long id){
        generoService.eliminar(id);
        return "redirect:/admin/genero/listar";
    }

    @GetMapping("/pelicula/eliminar/{id}")
    public String eliminarPelicula(@PathVariable("id") Long id){
        List<Opinion> opiniones=opinionService.findbyPeliculaId(id);
        for(Opinion obj:opiniones){
            opinionService.eliminar(obj.getId());
        }
        peliculaService.eliminar(id);
        return "redirect:/admin/pelicula/listar";
    }

    @GetMapping("/opinion/eliminar/{id}")
    public String eliminarOpiniones(@PathVariable("id") Long id){
        opinionService.eliminar(id);
        return "redirect:/admin/opinion/listar";
    }

    //Editar Usuarios,Generos,Peliculas,Categorias.
    @GetMapping("/usuario/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id,Model model){
       Usuario usuario= usuarioService.buscar(id);
       model.addAttribute("usuario",usuario);
       return "admin/usuario/editar";
    }

    @GetMapping("/genero/editar/{id}")
    public String editarGenero(@PathVariable("id") Long id,Model model){
        Genero genero= generoService.buscar(id);
        model.addAttribute("genero",genero);
        return "admin/genero/editar";
    }

    @GetMapping("/pelicula/editar/{id}")
    public String editarPelicula(@PathVariable("id") Long id,Model model){
        Pelicula pelicula= peliculaService.buscar(id);
        var clasificaciones=clasificacionService.listarClasificaciones();
        var generos=generoService.listarGeneros();
        model.addAttribute("clasificaciones",clasificaciones);
        model.addAttribute("generos",generos);
        model.addAttribute("pelicula",pelicula);
        return "admin/pelicula/editar";
    }

    @GetMapping("/opinion/editar/{id}")
    public String editarOpinion(@PathVariable("id") Long id,Model model){
        Opinion opinion= opinionService.buscar(id);
        model.addAttribute("opinion",opinion);
        return "admin/opinion/editar";
    }

    //Crear Usuarios,Generos,Peliculas,Categorias.

    /*INICIO USUARIO*/
    @PostMapping("/usuario/crear")
    public String crearUsuario(@Valid Usuario usuario, Errors errores){
        if(!errores.hasErrors()){
            Rol rol=rolService.buscar(2);
            usuario.setContrasena(codificarPassword(usuario.getContrasena()));
            usuario.setRol(rol);
            usuarioService.guardar(usuario);
            return "redirect:/admin/usuario/listar";
        }else{
            return "admin/usuario/editar";
        }
    }
    @GetMapping("/usuario/crear")
    public String agregarUsuario(Usuario usuario){
        return "admin/usuario/editar";
    }
    /*FIN USUARIO*/

    /*INICIO GENERO*/
    @PostMapping("/genero/crear")
    public String crearGenero(@Valid Genero genero, Errors errores){
        if(!errores.hasErrors()){
            generoService.guardar(genero);
            return "redirect:/admin/genero/listar";
        }else{
            return "admin/genero/editar";
        }
    }
    @GetMapping("/genero/crear")
    public String agregarGenero(Genero genero){
        return "admin/genero/editar";
    }
    /*FIN GENERO*/

    /*INICIO PELICULA*/
    @PostMapping("/pelicula/crear")
    public String crearPelicula(@Valid Pelicula pelicula,Errors errores, @RequestParam("file")MultipartFile imagen,Model model) throws IOException {
        var clasificaciones=clasificacionService.listarClasificaciones();
        var generos=generoService.listarGeneros();
        model.addAttribute("clasificaciones",clasificaciones);
        model.addAttribute("generos",generos);
        if(!errores.hasErrors()){
            peliculaService.guardar(pelicula);
            if(!imagen.isEmpty()){
                Path directorio= Paths.get("src//main//resource//static/img/pelicula");
                String absoluta= directorio.toFile().getAbsolutePath();
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta=Paths.get(absoluta+ "//"+imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesImg);
                pelicula.setImagen(imagen.getOriginalFilename());
            }
            return "redirect:/admin/pelicula/listar";
        }else{
            return "admin/pelicula/editar";
        }
    }
    @GetMapping("/pelicula/crear")
    public String agregarPelicula(Pelicula pelicula,Model model){
        var clasificaciones=clasificacionService.listarClasificaciones();
        var generos=generoService.listarGeneros();
        model.addAttribute("clasificaciones",clasificaciones);
        model.addAttribute("generos",generos);
        return "admin/pelicula/editar";
    }
    /*FIN PELICULA*/

    /*INICIO OPINION*/
    @PostMapping("/opinion/crear")
    public String crearOpinion(@Valid Opinion opinion, Errors errores){
        if(!errores.hasErrors()){
            opinionService.guardar(opinion);
            return "redirect:/admin/opinion/listar";
        }else{
            return "admin/opinion/editar";
        }
    }
    @GetMapping("/opinion/crear")
    public String agregarOpinion(Opinion opinion){
        return "admin/opinion/editar";
    }
    /*FIN OPINION*/


    //Buscar Usuarios,Generos,Peliculas,Categorias.



    //Codificar Contrasena
    public String codificarPassword(String password){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
