package edu.utp.peliculas.repository;

import edu.utp.peliculas.model.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion,Long> {
}
