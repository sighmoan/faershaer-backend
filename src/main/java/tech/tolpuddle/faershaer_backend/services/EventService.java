package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Service;
import tech.tolpuddle.faershaer_backend.domain.*;

import java.util.List;

@Service
public class EventService {
    private final EventRepo repo;
    private final PersonDbRepo personRepo;
    private final UserAccessor userAccessor;

    public EventService(EventRepo repo, UserAccessor userAccessor, PersonDbRepo personRepo) {
        this.repo = repo;
        this.userAccessor = userAccessor;
        this.personRepo = personRepo;
    }

    public Event createNewEvent(String label) {
        Event event = new Event();
        event.setLabel(label);
        Person p = new Person();
        p.setName(userAccessor.getUser().getName());
        p.setUser(userAccessor.getUser());
        event.setParticipants(List.of(p));

        personRepo.save(p);
        repo.save(event);

        p.setEvent(event);
        personRepo.save(p);
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
