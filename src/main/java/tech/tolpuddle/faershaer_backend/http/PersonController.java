package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.http.dtos.PersonDto;
import tech.tolpuddle.faershaer_backend.services.PersonService;
import tech.tolpuddle.faershaer_backend.services.TxService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonController {

    PersonService service;
    TxService txService;

    public PersonController(PersonService service, TxService txService) {
        this.service = service;
        this.txService = txService;
    }

    @GetMapping
    public List<PersonDto> getPersons() {
        return service.getAllPersons()
                .stream()
                .map((person) -> PersonDto.fromPerson(person, txService.getBalance(person)))
                .toList();
    }

    @PostMapping
    private ResponseEntity<Void> addPerson(@RequestBody String name) {
        service.addPerson(name);
        return ResponseEntity.created(URI.create("/persons")).build();
    }
}
