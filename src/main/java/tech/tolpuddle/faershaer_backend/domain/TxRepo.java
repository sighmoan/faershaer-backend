package tech.tolpuddle.faershaer_backend.domain;

import org.springframework.stereotype.Repository;
import tech.tolpuddle.faershaer_backend.services.EventAccessor;

import java.util.Arrays;
import java.util.List;

@Repository
public class TxRepo {
    TxDbRepo repo;
    EventAccessor eventAccessor;

    public TxRepo(TxDbRepo repo, EventAccessor eventAccessor) {
        this.repo = repo;
        this.eventAccessor = eventAccessor;
    }

    public List<Transaction> findAll() {
        return repo.findAllByEvent(eventAccessor.getEvent());
    }

    public void save(Transaction transaction) {
        transaction.setEvent(eventAccessor.getEvent());
        repo.save(transaction);
    }

    public void deleteById(String txId) {
        repo.deleteById(txId);
    }

    public List<Transaction> findAllByPayer(Person person) {
        return repo.findAllByEventAndPayer(eventAccessor.getEvent(), person);
    }
}
