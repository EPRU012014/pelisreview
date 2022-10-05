package edu.utp.peliculas.service;



import edu.utp.peliculas.model.Opinion;

import java.util.List;

public interface OpinionService {
    public List<Opinion> listarOpiniones();

    public List<Opinion> findbyPeliculaId(Long id);

    public List<Opinion> findbyUsuarioId(Long id);

    public Opinion guardar(Opinion opinion);

    public void eliminar(Long id);

    public Opinion buscar(Long id);
}
