package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.domain.Event;
import tech.tolpuddle.faershaer_backend.services.EventService;

import java.net.URI;

@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody String label) {
        Event event = eventService.createNewEvent(label);
        return ResponseEntity.created(URI.create("/events/" + event.getId())).build();
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(String eventId) {
        eventService.deleteById(eventId);
    }
}
