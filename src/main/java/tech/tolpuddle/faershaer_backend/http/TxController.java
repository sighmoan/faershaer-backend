package tech.tolpuddle.faershaer_backend.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
import tech.tolpuddle.faershaer_backend.domain.TxDbRepo;

import java.util.List;

@RestController
public class TxController {

    TxDbRepo repo;

    public TxController(TxDbRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return repo.findAll();
    }
}
