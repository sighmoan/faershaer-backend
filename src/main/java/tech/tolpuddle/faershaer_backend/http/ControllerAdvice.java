package tech.tolpuddle.faershaer_backend.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.RequestScope;
import tech.tolpuddle.faershaer_backend.domain.Event;
import tech.tolpuddle.faershaer_backend.domain.EventDbRepo;
import tech.tolpuddle.faershaer_backend.exceptions.DuplicatePersonException;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchEventException;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchPersonException;
import tech.tolpuddle.faershaer_backend.services.EventAccessor;

@Configuration
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    GenericApplicationContext context;
    EventDbRepo repo;

    public ControllerAdvice(GenericApplicationContext context, EventDbRepo repo) {
        this.context = context;
        this.repo = repo;
    }

    @Bean
    @RequestScope
    public EventAccessor registerEvent(HttpServletRequest req) {
        System.out.println(req.getRequestURI());
        String eventId = req.getRequestURI().split("/")[2];
        System.out.println(eventId);
        Event event = repo.findById(eventId).orElseThrow(NoSuchEventException::new);
        return new EventAccessor(event);
    }

    @ExceptionHandler(DuplicatePersonException.class)
    ResponseEntity<Void> handleIntegrityViolation() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NoSuchPersonException.class)
    ResponseEntity<Void> handleMissingPerson() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NoSuchEventException.class)
    ResponseEntity<Void> handleMissingEvent() {
        return ResponseEntity.notFound().build();
    }
}
