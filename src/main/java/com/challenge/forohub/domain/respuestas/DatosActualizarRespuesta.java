package com.challenge.forohub.domain.respuestas;

import lombok.NonNull;

public record DatosActualizarRespuesta(
        @NonNull
        Long id,
        String mensaje,
        Boolean solucion
) {
}
