package edu.utp.peliculas.controller;


import edu.utp.peliculas.model.Rol;
import edu.utp.peliculas.model.Usuario;
import edu.utp.peliculas.service.RolService;
import edu.utp.peliculas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ControladorUsuario {
    @Autowired
    private RolService rolService;
    @Autowired
    private UsuarioService usuarioService;


    //Crear Cuenta
    /*INICIO USUARIO*/
    @PostMapping("/crearCuenta")
    public String crearUsuario(@Valid Usuario usuario, Errors errores){
        if(!errores.hasErrors()){
            Rol rol=rolService.buscar(2);
            usuario.setContrasena(codificarPassword(usuario.getContrasena()));
            usuario.setRol(rol);
            usuarioService.guardar(usuario);
            return "redirect:/login";
        }else{
            return "/usuario/RegistroUsuario";
        }
    }
    @GetMapping("/registro")
    public String agregarUsuario(Usuario usuario){
        return "/usuario/RegistroUsuario";
    }
    /*FIN USUARIO*/

    //Codificar Contrasena
    public String codificarPassword(String password){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
