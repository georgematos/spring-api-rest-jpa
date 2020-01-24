package com.algaworks.comercialapi.resources;

import java.time.Instant;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResourceExceptionHandler
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<StandardError> emptyResultDataAccess(EmptyResultDataAccessException exception, HttpServletRequest resquest) {
        String error = "O recurso a ser deletado não foi encontrado.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> entityNotFound(NoSuchElementException exception, HttpServletRequest resquest) {
        String error = "Elemento não pode ser encontrado.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundForUpdate(EntityNotFoundException exception, HttpServletRequest resquest) {
        String error = "A entidade não foi encontrada para a atualização.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<StandardError> entityAlreadyExist(EntityExistsException exception, HttpServletRequest resquest) {
        String error = "Uma oportunidade igual a esta já foi cadastrada.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<StandardError> defaultException(Exception exception, HttpServletRequest resquest) {

        String error = "Um erro desconhecido ocorreu, por favor, entre em contato com o administrador.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
}
