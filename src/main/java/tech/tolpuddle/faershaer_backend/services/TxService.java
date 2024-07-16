package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Service;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
import tech.tolpuddle.faershaer_backend.domain.TxDbRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TxService {
    TxDbRepo repo;

    public TxService(TxDbRepo repo) {
        this.repo = repo;
    }

    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }

    public void addTransaction(Transaction transaction) {
        repo.save(transaction);
    }

    public void deleteTransaction(String txId) {
        repo.deleteById(txId);
    }

    public Double getBalance(Person person) {
        return repo.findAllByPayer(person)
                .stream()
                .map(Transaction::getSum)
                .reduce(0.0, Double::sum);
    }
}
