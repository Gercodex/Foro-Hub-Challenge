package com.challenge.forohub.domain.perfiles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarPerfil(
        @NotNull
        Long id,
        @NotBlank
        String nombre
) {
}
