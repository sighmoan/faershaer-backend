package tech.tolpuddle.faershaer_backend.http;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.services.PersonService;

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
}
