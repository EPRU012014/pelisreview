package edu.utp.peliculas.controller;

import edu.utp.peliculas.model.Opinion;
import edu.utp.peliculas.model.Pelicula;
import edu.utp.peliculas.model.Usuario;
import edu.utp.peliculas.service.GeneroService;
import edu.utp.peliculas.service.OpinionService;
import edu.utp.peliculas.service.PeliculaService;
import edu.utp.peliculas.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/pelicula")
public class ControladorPelicula {

    @Autowired
    private GeneroService generoService;
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private OpinionService opinionService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String listarPeliculas(Model model){
        List<Pelicula> peliculas=peliculaService.listarPeliculas();
        model.addAttribute("peliculas",peliculas);
        return "Paginas";
    }

    @GetMapping("/{id}")
    public String pelicula(@PathVariable("id") Long id, Opinion opinion, Model model){
        Pelicula pelicula=peliculaService.buscar(id);
        model.addAttribute("pelicula",pelicula);
        return "pelicula/PaginaIndividual";
    }

    @PostMapping("/opinion/agregar/{id}")
    public String agregarOpinion(Opinion opinion,@AuthenticationPrincipal User user,@PathVariable("id") Long id){
        log.info("este es el id: "+id);
        Pelicula pelicula=peliculaService.buscar(id);
        Usuario usuario=usuarioService.findByEmail(user.getUsername());
        opinion.setId(null);
        opinion.setPelicula(pelicula);
        opinion.setUsuario(usuario);
        opinionService.guardar(opinion);
        return "redirect:/pelicula/"+id;
    }

}
