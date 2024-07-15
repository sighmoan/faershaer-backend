package tech.tolpuddle.faershaer_backend.http;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
import tech.tolpuddle.faershaer_backend.domain.TxDbRepo;
import tech.tolpuddle.faershaer_backend.services.TxService;

import java.util.List;

@RestController
@CrossOrigin
public class TxController {

    TxService txService;

    public TxController(TxService txService) {
        this.txService = txService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return txService.getAllTransactions();
    }
}
