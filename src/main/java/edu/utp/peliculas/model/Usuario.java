package edu.utp.peliculas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="Por favor agregue un nombre")
    @Size(min=4, max=45, message="El nombre no es valido")
    @Column(name="primer_nombre")
    private String primerNombre;

    @Column(name="segundo_nombre")
    private String segundoNombre;

    @NotEmpty(message="Por favor agregue un apellido")
    @Size(min=1, max=45, message="El apellido no es valido")
    @Column(name="primer_apellido")
    private String primerApellido;

    @Column(name="segundo_apellido")
    private String segundoApellido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="fecha_nacimiento")
    private Date nacimiento;

    private Integer edad;

    @NotEmpty(message="Por favor agregue un email")
    @Size(min=1, max=70, message="El email es invalido")
    @Email(message="No es un correo valido. ")
    @Column(name="email")
    private String email; //No se coloca email por el tema de Spring security

    @NotEmpty(message="Por favor agregue una contrasena")
    @Size(min=1, max=128, message="La contraseña no es valida")
    private String contrasena;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "usuario",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private List<Opinion> opiniones;


}
