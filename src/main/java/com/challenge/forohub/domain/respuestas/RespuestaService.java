package com.challenge.forohub.domain.respuestas;

import com.challenge.forohub.domain.topicos.Topico;
import com.challenge.forohub.domain.topicos.TopicoRepository;
import com.challenge.forohub.domain.usuarios.Usuario;
import com.challenge.forohub.domain.usuarios.UsuarioRepository;
import com.challenge.forohub.utils.errores.ErrorDeConsulta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosListadoRespuestas agregarRespuesta(DatosRegistroRespuesta datosRegistroRespuesta) {
        Topico topico;
        Usuario usuario;
        //Validar topico_id e usuario_id -> construir la respuesta y salvar en repositorio.

        if (topicoRepository.findById(datosRegistroRespuesta.topico_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        if (!topicoRepository.findByStatusById(datosRegistroRespuesta.topico_id())) {
            throw new ErrorDeConsulta("No se halló el topico");
        }
        if (usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló el usuario");
        }
        topico = topicoRepository.findById(datosRegistroRespuesta.topico_id()).get();
        usuario = usuarioRepository.findById(datosRegistroRespuesta.usuario_id()).get();

        Respuesta respuestaI = new Respuesta(
                null,
                datosRegistroRespuesta.mensaje(),
                topico,
                LocalDateTime.now(),
                usuario,
                false
        );
        topico.agregarRespuesta(respuestaI);
        Respuesta respuesta = respuestaRepository.save(respuestaI);
        return new DatosListadoRespuestas(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFecha(),
                respuesta.getUsuario().getNombre(),
                respuesta.getSolucion()
        );

    }

    public Page<DatosListadoRespuestas> getRespuestas(Pageable pageable) {
        return respuestaRepository.findAll(pageable).map(DatosListadoRespuestas::new);
    }

    public void deleteRespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }

    public DatosListadoRespuestas getRespuesta(Long id) {
        if (respuestaRepository.findById(id).isEmpty()) {
            throw new ErrorDeConsulta("No se halló la respuesta");
        }
        Respuesta respuesta = respuestaRepository.findById(id).get();
        return new DatosListadoRespuestas(respuesta) ;
    }

    @Transactional
    public DatosListadoRespuestas actualizaRespuesta(DatosActualizarRespuesta datosActualizarRespuesta) {
        if (respuestaRepository.findById(datosActualizarRespuesta.id()).isEmpty()) {
            throw new ErrorDeConsulta("No se halló la respuesta");
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualiza(datosActualizarRespuesta);
        return new DatosListadoRespuestas(respuesta);
    }
}
