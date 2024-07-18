package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PersonDbRepo extends ListCrudRepository<Person, String> {
    Person findByEventAndName(Event event, String name);
    List<Person> findAllByEvent(Event event);
    List<Person> findAllByUser(User user);
}
