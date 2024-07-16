package tech.tolpuddle.faershaer_backend.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import tech.tolpuddle.faershaer_backend.domain.DuplicatePersonException;
import tech.tolpuddle.faershaer_backend.domain.Person;
import tech.tolpuddle.faershaer_backend.domain.PersonDbRepo;

import java.util.List;

@Service
public class PersonService {

    PersonDbRepo repo;

    public PersonService(PersonDbRepo repo) {
        this.repo = repo;
    }

    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    public void addPerson(String name) {
        Person p = new Person();
        p.setName(name);
        try {
            repo.save(p);
        } catch(DataIntegrityViolationException ex) {
            throw new DuplicatePersonException();
        }
    }
}
