package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface UserDbRepo extends ListCrudRepository<User, String> {
}
