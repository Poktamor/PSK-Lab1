package org.example.services;

import jakarta.transaction.Transactional;
import org.example.DTOs.JobDTO;
import org.example.DTOs.JobPostPutDTO;
import org.example.models.Job;
import org.example.models.Person;
import org.example.repositories.JobsRepository;
import org.example.repositories.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    final JobsRepository jobsRepository;
    final PersonRepository personRepository;

    public JobService(JobsRepository JobsRepository, PersonRepository PersonRepository) {
        jobsRepository = JobsRepository;
        personRepository = PersonRepository;
    }

    public ResponseEntity<?> getAll() {
        List<JobDTO> jobs = jobsRepository.findAll().stream().map(JobDTO::new).toList();
        return ResponseEntity.status(HttpStatus.FOUND).body(jobs);
    }
    public ResponseEntity<?> addNew(JobPostPutDTO newJob) {
        Job job = new Job();

        List<Person> people;

        if (newJob.peopleIds == null) {
            people = null;
        } else {
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
    public ResponseEntity<?> update(JobPostPutDTO newJob, Long newId){
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

    @Transactional
    public ResponseEntity<?> delete(Long id){
        Optional<Job> jobToDelete = jobsRepository.findById(id);

        if (jobToDelete.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        jobsRepository.delete(jobToDelete.get());
        return ResponseEntity.noContent().build();
    }
}
