package org.example.controllers;

import org.example.DTOs.PersonDTO;
import org.example.DTOs.PersonPostPutDTO;
import org.example.models.Person;
import org.example.models.Submarine;
import org.example.repositories.PersonRepository;
import org.example.repositories.SubmarineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    final PersonRepository personRepository;
    private final SubmarineRepository submarineRepository;

    PersonController(PersonRepository PersonRepository, SubmarineRepository submarineRepository) {
        this.personRepository = PersonRepository;
        this.submarineRepository = submarineRepository;
    }

    @GetMapping("api/People")
    public List<PersonDTO> getPeople() {
        return personRepository.findAll().stream().map(PersonDTO::new).toList();
    }

    @PostMapping("api/People")
    public ResponseEntity<?> postPerson(@RequestBody PersonPostPutDTO newPerson) {

        Optional<Submarine> submarineOptional = submarineRepository.findById(newPerson.submarineId);

        if (submarineOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Submarine not found with id: " + newPerson.submarineId);
        }

        Person person = new Person();
        person.setName(newPerson.name);
        person.setSurname(newPerson.surname);
        person.setSubmarine(submarineOptional.get());

        Person savedPerson = personRepository.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("api/People/{id}")
    public ResponseEntity<PersonDTO> putPerson(
            @RequestBody PersonPostPutDTO newPerson,
            @PathVariable Long id) {

        Submarine submarine = submarineRepository
                .findById(newPerson.submarineId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Submarine not found with id: " + newPerson.submarineId
                        )
                );

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Person not found with id: " + id
                        )
                );

        person.setName(newPerson.name);
        person.setSurname(newPerson.surname);
        person.setSubmarine(submarine);

        Person saved = personRepository.save(person);

        HttpStatus status = personRepository.existsById(id)
                ? HttpStatus.OK
                : HttpStatus.CREATED;

        return new ResponseEntity<>(new PersonDTO(saved), status);
    }

    @DeleteMapping("api/People/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
