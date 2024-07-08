package com.challenge.forohub.domain.perfiles;

import com.challenge.forohub.domain.cursos.DatosActualizarCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "perfiles")
@Entity(name = "Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Perfil(DatosRegistroPerfil datosRegistroPerfil) {
        this.nombre = datosRegistroPerfil.nombre();
    }

    public void actualizarPerfil(DatosActualizarPerfil datosActualizarPerfil) {
        if (datosActualizarPerfil.nombre() != null) {
            this.nombre = datosActualizarPerfil.nombre();
        }
    }
}
