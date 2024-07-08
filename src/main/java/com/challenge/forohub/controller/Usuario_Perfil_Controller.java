package com.challenge.forohub.controller;

import com.challenge.forohub.domain.usuario_perfil.DatosListado_Usuario_perfil;
import com.challenge.forohub.domain.usuario_perfil.DatosRegistro_Usuario_Perfil;
import com.challenge.forohub.domain.usuario_perfil.Usuario_Perfil_Service;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/usuarioperfil")
@SecurityRequirement(name = "bearer-key")
public class Usuario_Perfil_Controller {

    @Autowired
    private Usuario_Perfil_Service usuarioPerfilService;

    @PostMapping
    public ResponseEntity<DatosListado_Usuario_perfil> agregarUsuariosPerfiles(@RequestBody @Valid DatosRegistro_Usuario_Perfil datosRegistroUsuarioPerfil) {
        return ResponseEntity.ok(usuarioPerfilService.agregarPefil(datosRegistroUsuarioPerfil));
    }

    @GetMapping
    public ResponseEntity<List<DatosListado_Usuario_perfil>> mostrarPerfilesUsuarios() {
        return ResponseEntity.ok(usuarioPerfilService.mostrarUsuarioPerfil());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarUsuarioPerfil(@PathVariable Long id) {
        usuarioPerfilService.borrarUsuarioPerfil(id);
        return ResponseEntity.noContent().build();
    }

}
