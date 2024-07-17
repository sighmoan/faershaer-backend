package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TxDbRepo extends ListCrudRepository<Transaction, String> {
    List<Transaction> findAllByEvent(Event event);

    List<Transaction> findAllByEventAndPayer(Event event, Person person);
}
