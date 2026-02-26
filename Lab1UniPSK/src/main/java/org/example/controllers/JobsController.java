package org.example.controllers;

import org.apache.coyote.Response;
import org.example.DTOs.JobDTO;
import org.example.DTOs.JobPostPutDTO;
import org.example.models.Job;
import org.example.models.Person;
import org.example.repositories.JobsRepository;
import org.example.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class JobsController {
    final JobsRepository jobsRepository;
    final PersonRepository personRepository;

    public JobsController(JobsRepository JobsRepository, PersonRepository PersonRepository) {
        jobsRepository = JobsRepository;
        personRepository = PersonRepository;
    }

    @GetMapping("/api/Jobs")
    public List<JobDTO> getJobs() {
        return jobsRepository.findAll().stream().map(JobDTO::new).toList();
    }

    @PostMapping("/api/Jobs")
    public ResponseEntity<?> postJob(@RequestBody JobPostPutDTO newJob) {
        Job job = new Job();

        List<Person> people;

        if (newJob.peopleIds == null) {
            people = null;
        }
        else {
            people = newJob.peopleIds.stream().map((id) -> personRepository.findById(id).orElseThrow(
                    () ->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Person not found with id: " + id
                            )
            )).toList();
        }

        job.setName(newJob.name);
        job.setPeople(people);
        jobsRepository.save(job);

        return ResponseEntity.status(HttpStatus.CREATED).body(new JobDTO(job));
    }

    @PutMapping("/api/Jobs/{newId}")
    public ResponseEntity<?> putJob(@RequestBody JobPostPutDTO newJob, @PathVariable Long newId) {
        Job job = jobsRepository.findById(newId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found with id: " + newId));

        List<Person> people = newJob.peopleIds.stream()
                .map(id -> personRepository.findById(id).orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Person not found with id: " + id
                        )
                ))
                .collect(Collectors.toList());

        job.setName(newJob.name);
        job.setPeople(people);

        jobsRepository.save(job);
        return ResponseEntity.status(HttpStatus.OK).body(new JobDTO(job));
    }
}
