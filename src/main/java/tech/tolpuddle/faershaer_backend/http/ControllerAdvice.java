package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.tolpuddle.faershaer_backend.domain.DuplicatePersonException;
import tech.tolpuddle.faershaer_backend.domain.NoSuchPersonException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DuplicatePersonException.class)
    ResponseEntity<Void> handleIntegrityViolation() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NoSuchPersonException.class)
    ResponseEntity<Void> handleMissingPerson() {
        return ResponseEntity.badRequest().build();
    }
}
