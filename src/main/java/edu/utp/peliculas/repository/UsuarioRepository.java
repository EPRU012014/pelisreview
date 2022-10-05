package edu.utp.peliculas.repository;

import edu.utp.peliculas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query(value = "select * from usuario a where a.email =:email", nativeQuery = true)
    Usuario findByEmail(String email);
}
