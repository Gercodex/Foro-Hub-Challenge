package com.challenge.forohub.domain.perfiles;

public record DatosListadoPerfil(
        Long id,
        String nombre
) {
    public DatosListadoPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre());
    }
}
