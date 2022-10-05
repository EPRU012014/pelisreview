package edu.utp.peliculas.service;

import edu.utp.peliculas.model.Usuario;
import edu.utp.peliculas.repository.RolRepository;
import edu.utp.peliculas.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailService")
@Slf4j
public class UsuarioDetalle implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepository.findByEmail(email);
        if(usuario==null){
            throw new UsernameNotFoundException(email);
        }
        var rol=new ArrayList<GrantedAuthority>();
        log.info("Este es el rol "+usuario.getRol().getRole());
        rol.add(new SimpleGrantedAuthority(usuario.getRol().getRole()));
        return new User(usuario.getEmail(), usuario.getContrasena(), rol);
    }
}
