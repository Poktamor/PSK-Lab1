package org.example;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

@Repository
interface SubmarineRepository extends JpaRepository<Submarine, Long> {
    Submarine findByName(String name);
}
