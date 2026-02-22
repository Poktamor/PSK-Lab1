package org.example.DTOs;

import org.example.models.Submarine;

public class SubmarineDTO {

    public Long id;
    public String name;

    public SubmarineDTO(Submarine submarine) {
        this.id = submarine.getId();
        this.name = submarine.getName();
    }
}