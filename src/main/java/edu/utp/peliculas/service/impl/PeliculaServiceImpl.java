package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Pelicula;
import edu.utp.peliculas.repository.PeliculaRepository;
import edu.utp.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> listarPeliculas() {
        return (List<Pelicula>) peliculaRepository.findAll();
    }

    @Override
    @Transactional
    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula buscar(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByTitulo(String titulo) {
        return (List<Pelicula>) peliculaRepository.findByTitulo(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByArgumento(String argumento) {
        return (List<Pelicula>) peliculaRepository.findByArgumento(argumento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByClasificacion(String clasficacion) {
        return (List<Pelicula>) peliculaRepository.findByClasificacion(clasficacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findByGenero(String genero) {
        return (List<Pelicula>) peliculaRepository.findByGenero(genero);
    }
}
