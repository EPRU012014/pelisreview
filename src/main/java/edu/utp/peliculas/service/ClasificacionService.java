package edu.utp.peliculas.service;



import edu.utp.peliculas.model.Clasificacion;

import java.util.List;

public interface ClasificacionService {
    public List<Clasificacion> listarClasificaciones();

    public Clasificacion buscar(Long id);
}
