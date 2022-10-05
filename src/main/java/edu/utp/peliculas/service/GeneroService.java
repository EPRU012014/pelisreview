package edu.utp.peliculas.service;




import edu.utp.peliculas.model.Genero;

import java.util.List;

public interface GeneroService {
    public List<Genero> listarGeneros();

    public Genero guardar(Genero genero);

    public void eliminar(Long id);

    public Genero buscar(Long id);
}
