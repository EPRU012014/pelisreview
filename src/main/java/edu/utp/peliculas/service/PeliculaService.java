package edu.utp.peliculas.service;



import edu.utp.peliculas.model.Pelicula;

import java.util.List;

public interface PeliculaService {
    public List<Pelicula> listarPeliculas();

    public Pelicula guardar(Pelicula pelicula);

    public void eliminar(Long id);

    public Pelicula buscar(Long id);

    public List<Pelicula> findByTitulo(String titulo);

    public List<Pelicula> findByArgumento(String argumento);

    public List<Pelicula> findByClasificacion(String clasificacion);

    public List<Pelicula> findByGenero(String genero);
}
