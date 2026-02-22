package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Submarine {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "submarine")
    private List<Person> crew;


    public void setCrew(List<Person> crew) {
        this.crew = crew;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Person> getCrew() {
        return crew;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

