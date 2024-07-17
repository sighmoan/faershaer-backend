package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EventDbRepo extends ListCrudRepository<Event, String> {
    List<Event> getAllByParticipantsContaining(Person p);
}
