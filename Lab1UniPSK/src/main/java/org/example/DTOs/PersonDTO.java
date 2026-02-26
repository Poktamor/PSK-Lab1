package org.example.DTOs;

import org.example.models.Job;
import org.example.models.Person;

import java.util.List;

public class PersonDTO {

    public Long id;
    public String name;
    public String surname;
    public Long submarineId;
    public List<Long> jobIds;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.submarineId = person.getSubmarine() != null
                ? person.getSubmarine().getId()
                : null;

//        this.jobIds = person.getJobs()
//                .stream()
//                .map(Job::getId)
//                .toList();
    }

}