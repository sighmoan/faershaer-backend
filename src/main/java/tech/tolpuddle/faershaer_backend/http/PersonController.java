package tech.tolpuddle.faershaer_backend.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.services.PersonService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/persons")
public class PersonController {

    PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Person> getPersons() {
        return service.getAllPersons();
    }

    @PostMapping
    private ResponseEntity<Void> addPerson(@RequestBody String name) {
        service.addPerson(name);
        return ResponseEntity.created(URI.create("/persons")).build();
    }
}
