package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    final
    PersonRepository personRepository;

    PersonController(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    @GetMapping("api/getPeople")
    public List<Person> hello(){
        return personRepository.findAll();
    }
}
