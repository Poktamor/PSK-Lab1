package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne (fetch=FetchType.LAZY) @JoinColumn(name = "submarine_id")
    private Submarine submarine;

    @ManyToMany
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Submarine getSubmarine() {
        return submarine;
    }

    public void setSubmarine(Submarine submarine) {
        this.submarine = submarine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getId() {
        return id;
    }
}

