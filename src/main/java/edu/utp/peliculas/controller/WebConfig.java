package edu.utp.peliculas.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {

        //Cualquiera puede entrar a estas direcciones.
        registro.addViewController("/error/403").setViewName("/error/Template403");
        registro.addViewController("/").setViewName("PaginaPrincipal");
        //registro.addViewController("/registro").setViewName("usuario/RegistroUsuario");
        registro.addViewController("/login").setViewName("usuario/login");
    }
}
