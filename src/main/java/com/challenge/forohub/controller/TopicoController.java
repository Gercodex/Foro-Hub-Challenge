package com.challenge.forohub.controller;

import com.challenge.forohub.domain.respuestas.DTOpicoRespuestas;
import com.challenge.forohub.domain.topicos.DatosActualizarTopico;
import com.challenge.forohub.domain.topicos.DatosListadoTopico;
import com.challenge.forohub.domain.topicos.DatosRegistroTopico;
import com.challenge.forohub.domain.topicos.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> agregarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        DatosListadoTopico datosListadoTopico = topicoService.agregarTopico(datosRegistroTopico);
        return ResponseEntity.ok(datosListadoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> mostrarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoService.getTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> mostrarTopico(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.getTopico(id));
    }

    @PutMapping
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        return ResponseEntity.ok(topicoService.actualizaTopico(datosActualizarTopico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borraTopico(@PathVariable Long id) {
        topicoService.desactivaTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<Page<DTOpicoRespuestas>>  respuestasTopico(@PathVariable Long id, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicoService.getRespuestas(id, pageable));
    }
}
