package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Clasificacion;
import edu.utp.peliculas.repository.ClasificacionRepository;
import edu.utp.peliculas.service.ClasificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Clasificacion> listarClasificaciones() {
        return (List<Clasificacion>) clasificacionRepository.findAll() ;
    }

    @Override
    @Transactional(readOnly=true)
    public Clasificacion buscar(Long id) {
        return clasificacionRepository.findById(id).orElse(null);
    }
}
