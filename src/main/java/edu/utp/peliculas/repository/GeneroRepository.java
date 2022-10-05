package edu.utp.peliculas.repository;

import edu.utp.peliculas.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long> {
}
