package com.challenge.forohub.controller;

import com.challenge.forohub.domain.usuarios.*;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosListadoUsuarios> agregarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datosRegistroUsuario);
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosListadoUsuarios datosListadoUsuarios = new DatosListadoUsuarios(
                usuario.getId(),
                usuario.getNombre()
        );
        URI url = uriComponentsBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoUsuarios);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> mostrarUsuarios(@PageableDefault(size = 10) Pageable pageable) {
        Page<DatosListadoUsuarios> listadoUsuarios = usuarioRepository.findAll(pageable).map(DatosListadoUsuarios::new);
        return ResponseEntity.ok(listadoUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuarios> muestraUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        DatosListadoUsuarios datosListadoUsuarios = new DatosListadoUsuarios(
                usuario.getId(),
                usuario.getNombre()
        );
        /*List<String> lista = usuario.getPerfiles().stream().map(Perfil::getNombre).toList();
        System.out.println(lista);  // prueba de código arbitraria para extraer el ó los perfiles desde el usuario  */
        return ResponseEntity.ok(datosListadoUsuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosListadoUsuarios> actualizaUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarUsuario(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosListadoUsuarios(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
