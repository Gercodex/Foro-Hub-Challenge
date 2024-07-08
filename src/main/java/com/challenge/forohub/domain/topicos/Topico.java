package com.challenge.forohub.domain.topicos;

import com.challenge.forohub.domain.cursos.Curso;
import com.challenge.forohub.domain.respuestas.Respuesta;
import com.challenge.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    private List<Respuesta> respuestaList;

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        Boolean check = false;
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
            check = true;
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
            check = true;
        }
        if (check) {
            this.fecha = LocalDateTime.now();
        }
    }

    public void desactivaTopico() {
        this.status = false;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        respuestaList.add(respuesta);
    }
}
