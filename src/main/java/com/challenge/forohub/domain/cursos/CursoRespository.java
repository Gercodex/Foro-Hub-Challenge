package com.challenge.forohub.domain.cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CursoRespository extends JpaRepository<Curso,Long> {

    Page<Curso> findAll(Pageable pageable);

    @Query("SELECT c FROM Curso c WHERE c.nombre ILIKE :nombre")
    Optional<Curso> findByNombre(String nombre);

}
