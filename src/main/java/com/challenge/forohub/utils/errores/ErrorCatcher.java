package com.challenge.forohub.utils.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorCatcher {

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity catchInternalAuthSE(InternalAuthenticationServiceException e) {
        var msj = "Error de credenciales usuario";
        return ResponseEntity.badRequest().body(msj);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity catchInternalAuthSE(BadCredentialsException e) {
        var msj = "Error de credenciales password";
        return ResponseEntity.badRequest().body(msj);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity catchNullPointerException() {
        return ResponseEntity.notFound().build();
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity catchException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }*/

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity catchSQLICVE(SQLIntegrityConstraintViolationException e) {
        var res = e.getMessage();
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var error = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ErrorDeConsulta.class)
    public ResponseEntity errorDeConsulta(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidacion(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosErrorValidacion(String campo, String error) {
        private DatosErrorValidacion(
                FieldError fieldError
        ) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
