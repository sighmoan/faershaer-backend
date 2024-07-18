package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Service;
import tech.tolpuddle.faershaer_backend.domain.Event;
import tech.tolpuddle.faershaer_backend.domain.EventRepo;

import java.util.List;

@Service
public class EventService {
    private final EventRepo repo;

    public EventService(EventRepo repo) {
        this.repo = repo;
    }

    public Event createNewEvent(String label) {
        Event event = new Event();
        event.setLabel(label);
        repo.save(event);
        return event;
    }

    public void deleteById(String eventId) {
        repo.deleteById(eventId);
    }

    public List<Event> getEvents() {
        return repo.findAll();
    }

    public Event get(String eventId) {
        return repo.findById(eventId);
    }
}
