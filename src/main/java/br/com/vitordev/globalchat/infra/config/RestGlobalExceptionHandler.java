package br.com.vitordev.globalchat.infra.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.vitordev.globalchat.domain.exception.UserAlreadyExistsException;
import br.com.vitordev.globalchat.domain.exception.UserInvalidCredentialsException;
import br.com.vitordev.globalchat.domain.exception.rest.RestSimpleException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestGlobalExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestSimpleException> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return sendSimpleRestException(e, e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestSimpleException> DataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        return sendSimpleRestException(e,  "usuário com as mesmas credencias já cadastrado.");
    }

    @ExceptionHandler(UserInvalidCredentialsException.class)
    public ResponseEntity<RestSimpleException> UserInvalidCredentialsExceptionHandler(UserInvalidCredentialsException e) {
        return sendSimpleRestException(e, e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestSimpleException> EntityNotFoundExceptionHandler(EntityNotFoundException e) {
        return sendSimpleRestException(e, e.getMessage());
    }


    private ResponseEntity<RestSimpleException> sendSimpleRestException(Exception e, String message) {
        return ResponseEntity.badRequest().body(new RestSimpleException(HttpStatus.BAD_REQUEST.value(), message));
    }
}
