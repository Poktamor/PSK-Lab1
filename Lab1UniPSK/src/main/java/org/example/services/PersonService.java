package org.example.services;

import org.example.DTOs.PersonDTO;
import org.example.DTOs.PersonPostPutDTO;
import org.example.models.Job;
import org.example.models.Person;
import org.example.models.Submarine;
import org.example.repositories.JobsRepository;
import org.example.repositories.PersonRepository;
import org.example.repositories.SubmarineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final SubmarineRepository submarineRepository;
    private final JobsRepository jobsRepository;

    PersonService(PersonRepository PersonRepository, SubmarineRepository SubmarineRepository, JobsRepository JobsRepository) {
        this.personRepository = PersonRepository;
        this.submarineRepository = SubmarineRepository;
        this.jobsRepository = JobsRepository;
    }

    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.FOUND).body(personRepository.findAll().stream().map(PersonDTO::new).toList());
    }

    public ResponseEntity<?> addNew(PersonPostPutDTO newPerson) {
        Optional<Submarine> submarineOptional = submarineRepository.findById(newPerson.submarineId);

        if (submarineOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Submarine not found with id: " + newPerson.submarineId);
        }


        List<Job> jobs = newPerson.jobIds.stream()
                .map(jobId -> jobsRepository.findById(jobId)
                        .orElseThrow(() -> new RuntimeException("Job not found: " + jobId)))
                .toList();

        Person person = new Person();
        person.setName(newPerson.name);
        person.setSurname(newPerson.surname);
        person.setSubmarine(submarineOptional.get());
        person.setJobs(jobs);
        Person savedPerson = personRepository.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonDTO(savedPerson));
    }

    public ResponseEntity<?> update(PersonPostPutDTO newPerson, Long id) {
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

        List<Job> jobs = newPerson.jobIds.stream()
                .map(jobId -> jobsRepository.findById(jobId)
                        .orElseThrow(() -> new RuntimeException("Job not found: " + jobId)))
                .collect(java.util.stream.Collectors.toList());

        person.setJobs(jobs);
        person.setName(newPerson.name);
        person.setSurname(newPerson.surname);
        person.setSubmarine(submarine);

        Person saved = personRepository.save(person);

        HttpStatus status = personRepository.existsById(id)
                ? HttpStatus.OK
                : HttpStatus.CREATED;

        return new ResponseEntity<>(new PersonDTO(saved), status);
    }

    public ResponseEntity<?> delete(Long id){
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
