package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PersonDbRepo extends ListCrudRepository<Person, String> {
    Person findByEventsContainingAndName(Event event, String name);
    List<Person> findAllByEventsContaining(Event event);
}
