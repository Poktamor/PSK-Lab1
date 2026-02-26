package org.example.DTOs;

import org.example.models.Job;

import java.util.List;

public class JobDTO {
    public long id;
    public String name;
    public List<Long> peopleIds;

    public JobDTO(Job job) {
        this.id = job.getId();
        this.name = job.getName();
        this.peopleIds = job.getPeopleIds();
    }
}
