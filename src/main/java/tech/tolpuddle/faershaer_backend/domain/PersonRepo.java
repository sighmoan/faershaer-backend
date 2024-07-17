package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.stereotype.Repository;
import tech.tolpuddle.faershaer_backend.exceptions.NoSuchPersonException;
import tech.tolpuddle.faershaer_backend.services.EventAccessor;

import java.util.List;

@Repository
public class PersonRepo {
    private PersonDbRepo repo;
    private EventDbRepo eventRepo;
    EventAccessor eventAccessor;

    public PersonRepo(PersonDbRepo repo, EventAccessor eventAccessor, EventDbRepo eventRepo) {
        this.repo = repo;
        this.eventAccessor = eventAccessor;
        this.eventRepo = eventRepo;
    }


    public List<Person> findAll() {
        return eventAccessor.getEvent().getParticipants();
    }

    public void save(Person p) {
        eventAccessor.getEvent().getParticipants().add(p);
        repo.save(p);
        eventRepo.save(eventAccessor.getEvent());

    }

    public Person findById(String id) {
        return repo.findById(id).orElseThrow(NoSuchPersonException::new);
    }

    public void deleteById(String id) {
        Person p = repo.findById(id).orElseThrow(NoSuchPersonException::new);
        Event e = eventAccessor.getEvent();

        e.getParticipants().remove(p);

        if(eventRepo.getAllByParticipantsContaining(p).isEmpty()) {
            repo.delete(p);
        } else {
            repo.save(p);
        }

        eventRepo.save(e);
    }
}
