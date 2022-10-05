package edu.utp.peliculas.service;


import edu.utp.peliculas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioService{
    public ArrayList<Usuario> listarUsuarios();

    public void guardar(Usuario usuario);

    public void eliminar(Long id);

    public Usuario buscar(Long id);

    public Usuario findByEmail(String email);
}
