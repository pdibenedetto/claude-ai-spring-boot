package pl.piomin.services.claudeai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.claudeai.entity.Person;
import pl.piomin.services.claudeai.service.PersonService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        return person.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.created(URI.create("/api/persons/" + savedPerson.getId()))
                .body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> existingPerson = personService.findById(id);
        if (existingPerson.isPresent()) {
            Person updatedPerson = existingPerson.get();
            updatedPerson.setFirstName(person.getFirstName());
            updatedPerson.setLastName(person.getLastName());
            updatedPerson.setEmail(person.getEmail());
            updatedPerson.setPhone(person.getPhone());
            Person savedPerson = personService.save(updatedPerson);
            return ResponseEntity.ok(savedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            personService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
