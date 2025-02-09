package com.agrsystems.forohub.model;

import com.agrsystems.forohub.dto.topico.DatosActualizarTopico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter         //CREA TODOS LOS GETTERS
@NoArgsConstructor  //CREA UN CONSTRUCTOR SIN ARGUMENTOS
@AllArgsConstructor //CONSTRUCTOR CON ARGUMENTOS
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;
    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @NotBlank(message = "El status no puede estar vacío")
    private String status;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    @NotNull(message = "El autor es obligatorio")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @NotNull(message = "El curso es obligatorio")
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
    }
}
