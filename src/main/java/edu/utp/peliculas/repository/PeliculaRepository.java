package edu.utp.peliculas.repository;

import edu.utp.peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Long> {

    @Query(value = "select * from pelicula a where a.titulo like %:titulo% ", nativeQuery = true)
    public List<Pelicula> findByTitulo(String titulo);

    @Query(value = "select * from pelicula a where a.argumento like %:argumento% ", nativeQuery = true)
    public List<Pelicula> findByArgumento(String argumento);

    @Query(value = "select pelicula.*,clasificacion.nombre as clasificacion\n" +
            "from pelicula\n" +
            "inner join clasificacion\n" +
            "on pelicula.clasificacion_id = clasificacion.id \n" +
            "where clasificacion.nombre=:clasificacion", nativeQuery = true)
    public List<Pelicula> findByClasificacion(String clasificacion);
    @Query(value = "select pelicula.*\n" +
            "from pelicula \n" +
            "inner join pelicula_genero \n" +
            "on pelicula_genero.pelicula_id = pelicula.id \n" +
            "inner join genero\n" +
            "on pelicula_genero.genero_id = genero.id \n" +
            "where genero.nombre =:genero",nativeQuery = true)
    public List<Pelicula> findByGenero(String genero);



}
