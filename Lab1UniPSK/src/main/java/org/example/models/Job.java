package org.example.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @ManyToMany (fetch=FetchType.LAZY)
    private List<Person> people;

    public List<Long> getPeopleIds() {
        if (people == null) {
            return List.of(); // returns empty immutable list
        }
        return people.stream()
                .map(Person::getId)
                .toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

