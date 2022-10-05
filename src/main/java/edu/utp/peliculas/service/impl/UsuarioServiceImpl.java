package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Usuario;
import edu.utp.peliculas.repository.UsuarioRepository;
import edu.utp.peliculas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
