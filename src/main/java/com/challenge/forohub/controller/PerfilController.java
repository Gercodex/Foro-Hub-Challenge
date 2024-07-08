package com.challenge.forohub.controller;

import com.challenge.forohub.domain.perfiles.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRespository perfilRespository;

    @PostMapping
    public ResponseEntity<DatosListadoPerfil> agregarPerfil(@RequestBody @Valid DatosRegistroPerfil datosRegistroPerfil, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroPerfil);
        Perfil perfil = perfilRespository.save(new Perfil(datosRegistroPerfil));
        DatosListadoPerfil datosListadoPerfil = new DatosListadoPerfil(
                perfil.getId(),
                perfil.getNombre()
        );
        URI url = uriComponentsBuilder.path("perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoPerfil);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPerfil>> mostrarPerfil(@PageableDefault(size = 10) Pageable pageable) {
        Page<DatosListadoPerfil> listadoPerfils = perfilRespository.findAll(pageable).map(DatosListadoPerfil::new);
        return ResponseEntity.ok(listadoPerfils);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoPerfil> muestraPerfil(@PathVariable Long id) {
        Perfil perfil = perfilRespository.getReferenceById(id);
        DatosListadoPerfil datosListadoPerfil = new DatosListadoPerfil(
                perfil.getId(),
                perfil.getNombre()
        );
        return ResponseEntity.ok(datosListadoPerfil);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosListadoPerfil> actualizaPerfil(@RequestBody @Valid DatosActualizarPerfil datosActualizarPerfil) {
        Perfil perfil = perfilRespository.getReferenceById(datosActualizarPerfil.id());
        perfil.actualizarPerfil(datosActualizarPerfil);
        return ResponseEntity.ok(new DatosListadoPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPerfil(@PathVariable Long id) {
        perfilRespository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
