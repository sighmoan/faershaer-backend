package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tech.tolpuddle.faershaer_backend.domain.*;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TxService {
    TxRepo repo;

    public TxService(TxRepo repo) {
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
