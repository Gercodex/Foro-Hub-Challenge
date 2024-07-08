package com.challenge.forohub.domain.cursos;

public record DatosListadoCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
