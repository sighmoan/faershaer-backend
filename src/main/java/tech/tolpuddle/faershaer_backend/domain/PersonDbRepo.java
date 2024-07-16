package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface PersonDbRepo extends ListCrudRepository<Person, String> {
    Person findByName(String name);
}
