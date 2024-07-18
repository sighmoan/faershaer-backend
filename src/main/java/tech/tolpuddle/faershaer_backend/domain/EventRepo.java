package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.stereotype.Repository;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchEventException;
import tech.tolpuddle.faershaer_backend.services.UserAccessor;

import java.util.List;

@Repository
public class EventRepo {
    EventDbRepo repo;
    PersonDbRepo personRepo;
    UserAccessor userAccessor;

    public EventRepo(EventDbRepo repo, PersonDbRepo personRepo, UserAccessor userAccessor) {
        this.repo = repo;
        this.personRepo = personRepo;
        this.userAccessor = userAccessor;
    }

    public void save(Event event) {
        repo.save(event);
    }

    public void deleteById(String eventId) {
        repo.deleteById(eventId);
    }

    public List<Event> findAll() {
        List<Person> persons = personRepo.findAllByUser(userAccessor.getUser());
        return persons.stream().map(Person::getEvent).toList();
    }

    public Event findById(String id) {
        return repo.findById(id).orElseThrow(NoSuchEventException::new);
    }
}
