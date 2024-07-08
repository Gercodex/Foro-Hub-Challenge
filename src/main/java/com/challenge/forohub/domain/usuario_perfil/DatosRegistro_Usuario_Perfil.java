package com.challenge.forohub.domain.usuario_perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistro_Usuario_Perfil(
        @NotNull
        Long usuario_id,
        @NotNull
        Long perfil_id
) {
}
