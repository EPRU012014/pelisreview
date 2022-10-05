package edu.utp.peliculas.service.impl;


import edu.utp.peliculas.model.Rol;
import edu.utp.peliculas.repository.RolRepository;
import edu.utp.peliculas.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Rol buscar(int id) {
        return rolRepository.findById(id).orElse(null);
    }
}
