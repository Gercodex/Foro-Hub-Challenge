package com.challenge.forohub.domain.topicos;

import com.challenge.forohub.domain.respuestas.Respuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t.status FROM Topico t WHERE t.id =:topicoId")
    Boolean findByStatusById(Long topicoId);

    @Query("SELECT t FROM Topico t WHERE t.status = TRUE")
    Page<Topico> findAllByStatusTrue(Pageable pageable);

    @Query("SELECT r.respuestaList FROM Topico r WHERE r.id =:id_topico")
    Page<Respuesta> findAllByRespuestas(Long id_topico, Pageable pageable);
}
