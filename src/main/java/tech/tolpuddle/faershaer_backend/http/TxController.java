package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.domain.Transaction;
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

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionDto dto) {
        txService.addTransaction(dto.getTransaction());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{txId}")
    private ResponseEntity<Void> deleteTransaction(@PathVariable String txId) {
        txService.deleteTransaction(txId);
        return ResponseEntity.noContent().build();
    }
}
