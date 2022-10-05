package edu.utp.peliculas.repository;

import edu.utp.peliculas.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion,Long> {
    @Query(value = "select * from opinion a where a.pelicula_id =:id", nativeQuery = true)
    public List<Opinion> findbyPeliculaId(Long id);

    @Query(value = "select * from opinion a where a.usuario_id =:id", nativeQuery = true)
    public List<Opinion> findbyUsuarioId(Long id);
}
