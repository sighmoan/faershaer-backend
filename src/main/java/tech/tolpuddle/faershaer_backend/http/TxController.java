package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.http.dtos.AddTransactionDto;
import tech.tolpuddle.faershaer_backend.http.dtos.TransactionDto;
import tech.tolpuddle.faershaer_backend.services.PersonService;
import tech.tolpuddle.faershaer_backend.services.TxService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(("/transactions"))
public class TxController {

    TxService txService;
    PersonService personService;

    public TxController(TxService txService, PersonService personService) {
        this.txService = txService;
        this.personService = personService;
    }

    @GetMapping
    public List<TransactionDto> getTransactions() {
        return txService.getAllTransactions().stream().map((TransactionDto::getDto)).toList();
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody AddTransactionDto dto) {
        txService.addTransaction(dto.getTransaction(personService.getById(dto.payerId())));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{txId}")
    private ResponseEntity<Void> deleteTransaction(@PathVariable String txId) {
        txService.deleteTransaction(txId);
        return ResponseEntity.noContent().build();
    }
}
