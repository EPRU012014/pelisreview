package edu.utp.peliculas.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="genero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Por favor agregue un nombre al genero")
	@Size(max=45, message="No se puede colocar un nombre tan largo.")
	private String nombre;

}
