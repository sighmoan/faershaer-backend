package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.tolpuddle.faershaer_backend.domain.DuplicatePersonException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DuplicatePersonException.class)
    ResponseEntity<Void> handleIntegrityViolation() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
