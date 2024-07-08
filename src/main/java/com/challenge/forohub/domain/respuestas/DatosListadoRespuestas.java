package com.challenge.forohub.domain.respuestas;

import java.time.LocalDateTime;

public record DatosListadoRespuestas(
        Long id,
        String mensaje,
        String nombreTopico,
        LocalDateTime fecha,
        String nombreUsuario,
        Boolean solucion
) {
    public DatosListadoRespuestas(Respuesta respuesta) {
        this(respuesta.getId() ,respuesta.getMensaje(), respuesta.getTopico().getTitulo(), respuesta.getFecha(), respuesta.getUsuario().getNombre(), respuesta.getSolucion());
    }
}
