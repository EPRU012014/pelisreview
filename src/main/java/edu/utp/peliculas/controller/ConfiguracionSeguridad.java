package edu.utp.peliculas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                .antMatchers("/usuario/**","/pelicula/**")//Cualquiera con los roles de abajo puede ingresar aqui.
                .hasAnyRole("ADMIN", "USER")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    //.loginProcessingUrl("/login")
                .and()
                    .exceptionHandling().accessDeniedPage("/error/403");
    }
}
