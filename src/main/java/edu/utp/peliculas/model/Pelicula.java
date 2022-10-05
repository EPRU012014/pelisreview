package edu.utp.peliculas.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="pelicula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Por favor agregue un titulo")
	@Size(max=45, message="No se puede colocar un nombre tan largo.")
	private String titulo;

	@NotEmpty(message="Por favor agregue un argumento o sinopsis de la pelicula.")
	private String argumento;

	@Min(value=1, message="La duracion no tiene un valor permitido.")
	private int duracion;

	@Min(value=1, message="el año no tiene un valor permitido.")
	private int ano;

	private double puntuacion;

	@Lob
	@Column(name="imagen")
	private String imagen;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="clasificacion_id")
	private Clasificacion clasificacion;

	@ManyToMany
	@JoinTable(name = "pelicula_genero",
			joinColumns = @JoinColumn(name = "pelicula_id"),
			inverseJoinColumns = @JoinColumn(name = "genero_id"))
	private List<Genero> generos;

	@OneToMany(mappedBy = "pelicula",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Opinion> opiniones;


	public void agregarOpiniones(Opinion opinion){
		if(opiniones==null){
			opiniones=new ArrayList<>();
		}
		opiniones.add(opinion);

		opinion.setPelicula(this);
	}

}
