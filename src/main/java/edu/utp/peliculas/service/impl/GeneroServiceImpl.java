package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Genero;
import edu.utp.peliculas.repository.GeneroRepository;
import edu.utp.peliculas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Genero> listarGeneros() {
        return (List<Genero>) generoRepository.findAll();
    }

    @Override
    @Transactional
    public Genero guardar(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Genero buscar(Long id) {
        return generoRepository.findById(id).orElse(null);
    }
}
