package org.example.controllers;

import org.example.DTOs.PersonPostPutDTO;
import org.example.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonService personService;

    PersonController(PersonService PersonService) {
        this.personService = PersonService;
    }

    @GetMapping("api/People")
    public ResponseEntity<?> getPeople() {
        return personService.getAll();
    }

    @PostMapping("api/People")
    public ResponseEntity<?> postPerson(@RequestBody PersonPostPutDTO newPerson) {
        return personService.addNew(newPerson);
    }

    @PutMapping("api/People/{id}")
    public ResponseEntity<?> putPerson(
            @RequestBody PersonPostPutDTO newPerson,
            @PathVariable Long id) {
        return personService.update(newPerson, id);
    }

    @DeleteMapping("api/People/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        return personService.delete(id);
    }
}
