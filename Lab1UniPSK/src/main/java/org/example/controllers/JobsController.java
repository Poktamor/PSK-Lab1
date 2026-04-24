package org.example.controllers;

import org.example.DTOs.JobPostPutDTO;
import org.example.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobsController {
    private final JobService jobService;
    public JobsController(JobService JobService) {
        jobService = JobService;
    }

    @GetMapping("/api/Jobs")
    public ResponseEntity<?> getJobs() {
        return jobService.getAll();
    }

    @PostMapping("/api/Jobs")
    public ResponseEntity<?> postJob(@RequestBody JobPostPutDTO newJob) {
        return jobService.addNew(newJob);
    }

    @PutMapping("/api/Jobs/{newId}")
    public ResponseEntity<?> putJob(@RequestBody JobPostPutDTO newJob, @PathVariable Long newId) {
        return jobService.update(newJob, newId);
    }
}
