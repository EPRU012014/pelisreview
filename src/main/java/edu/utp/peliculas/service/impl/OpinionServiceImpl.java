package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Opinion;
import edu.utp.peliculas.repository.OpinionRepository;
import edu.utp.peliculas.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Opinion> listarOpiniones() {
        return (List<Opinion>) opinionRepository.findAll();
    }

    @Override
    public List<Opinion> findbyPeliculaId(Long id) {
        return (List<Opinion>) opinionRepository.findbyPeliculaId(id);
    }

    @Override
    public List<Opinion> findbyUsuarioId(Long id) {
        return (List<Opinion>) opinionRepository.findbyUsuarioId(id);
    }


    @Override
    @Transactional
    public Opinion guardar(Opinion opinion) {
        return opinionRepository.save(opinion);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        opinionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Opinion buscar(Long id) {
        return opinionRepository.findById(id).orElse(null);
    }
}
