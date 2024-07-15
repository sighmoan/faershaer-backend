package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Service;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
import tech.tolpuddle.faershaer_backend.domain.TxDbRepo;

import java.util.List;

@Service
public class TxService {
    TxDbRepo repo;

    public TxService(TxDbRepo repo) {
        this.repo = repo;
    }

    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }
}
