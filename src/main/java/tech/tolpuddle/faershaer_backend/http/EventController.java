package tech.tolpuddle.faershaer_backend.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.domain.Event;
import tech.tolpuddle.faershaer_backend.http.dtos.EventDto;
import tech.tolpuddle.faershaer_backend.services.EventService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(exposedHeaders = {"Location"})
public class EventController {

    Logger logger = LogManager.getLogger();

    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> getEvents() {
        logger.debug("getting events");
        return eventService.getEvents().stream().map(EventDto::getDto).toList();
    }

    @GetMapping("/{eventId}")
    public EventDto getThisEvent(@PathVariable String eventId) {
        return EventDto.getDto(eventService.get(eventId));
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody String label) {
        Event event = eventService.createNewEvent(label);
        return ResponseEntity.created(URI.create("/events/" + event.getId())).build();
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable String eventId) {
        eventService.deleteById(eventId);
    }
}
