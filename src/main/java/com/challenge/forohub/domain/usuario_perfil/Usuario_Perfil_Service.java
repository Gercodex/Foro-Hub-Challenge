package com.challenge.forohub.domain.usuario_perfil;

import com.challenge.forohub.domain.perfiles.Perfil;
import com.challenge.forohub.domain.perfiles.PerfilRespository;
import com.challenge.forohub.domain.usuarios.Usuario;
import com.challenge.forohub.domain.usuarios.UsuarioRepository;
import com.challenge.forohub.utils.errores.ErrorDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Usuario_Perfil_Service {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRespository perfilRespository;

    @Autowired
    private Usuario_Perfil_Repository usuarioPerfilRepository;

    public DatosListado_Usuario_perfil agregarPefil(DatosRegistro_Usuario_Perfil datosRegistroUsuarioPerfil) {

        Usuario usuario;
        Perfil perfil;

        if (usuarioRepository.findById(datosRegistroUsuarioPerfil.usuario_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el usuario");
        }
        if (perfilRespository.findById(datosRegistroUsuarioPerfil.perfil_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el perfil");
        }

        usuario = usuarioRepository.getReferenceById(datosRegistroUsuarioPerfil.usuario_id());
        perfil = perfilRespository.getReferenceById(datosRegistroUsuarioPerfil.perfil_id());

        Usuario_Perfil usuarioPerfil = new Usuario_Perfil(null, usuario, perfil);

        Usuario_Perfil usuarioPerfil1 = usuarioPerfilRepository.save(usuarioPerfil);

        return new DatosListado_Usuario_perfil(usuarioPerfil1);
    }

    public List<DatosListado_Usuario_perfil> mostrarUsuarioPerfil() {
        return usuarioPerfilRepository.findAll().stream().map(DatosListado_Usuario_perfil::new).toList();
    }

    public void borrarUsuarioPerfil(Long id) {
        usuarioPerfilRepository.deleteById(id);
    }
}
