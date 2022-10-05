package edu.utp.peliculas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="clasificacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clasificacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="Por favor agregue un nombre a la clasficacion.")
    @Size(max=45, message="No se puede colocar un nombre tan largo.")
    private String nombre;

    @Max(value=130, message = "La edad minima no es valida.")
    @Column(name="edad_minima")
    private String edadMinima;

    @OneToMany(mappedBy = "clasificacion",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Pelicula> peliculas;

    public void agregarPeliculas(Pelicula pelicula){
        if(peliculas==null){
            peliculas=new ArrayList<>();
        }
        peliculas.add(pelicula);

        pelicula.setClasificacion(this);
    }
}
