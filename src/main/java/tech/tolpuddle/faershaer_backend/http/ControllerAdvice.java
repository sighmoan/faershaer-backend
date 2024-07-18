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
import tech.tolpuddle.faershaer_backend.domain.User;
import tech.tolpuddle.faershaer_backend.domain.UserDbRepo;
import tech.tolpuddle.faershaer_backend.exceptions.DuplicatePersonException;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchEventException;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchPersonException;
import tech.tolpuddle.faershaer_backend.services.EventAccessor;
import tech.tolpuddle.faershaer_backend.services.UserAccessor;

@Configuration
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    GenericApplicationContext context;
    EventDbRepo repo;
    UserDbRepo userRepo;

    public ControllerAdvice(GenericApplicationContext context, EventDbRepo repo, UserDbRepo userRepo) {
        this.context = context;
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @Bean
    @RequestScope
    public EventAccessor registerEvent(HttpServletRequest req) {
        String eventId = req.getRequestURI().split("/")[2];
        Event event = repo.findById(eventId).orElseThrow(NoSuchEventException::new);
        return new EventAccessor(event);
    }

    @Bean
    @RequestScope
    public UserAccessor registerUser(HttpServletRequest req) {
        String userId = "1";
        if(req.getHeader("Authorization") != null) {
            userId = req.getHeader("Authorization");
        }
        User user = userRepo.findById(userId).orElseThrow();
        return new UserAccessor(user);
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
