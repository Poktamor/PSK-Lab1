package org.example.controllers;

import org.example.DTOs.SubmarineDTO;
import org.example.DTOs.SubmarinePostPutDTO;
import org.example.models.Submarine;
import org.example.repositories.SubmarineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SubmarineController {

    final
    SubmarineRepository submarineRepository;

    SubmarineController(SubmarineRepository SubmarineRepository) {
        this.submarineRepository = SubmarineRepository;
    }

    @GetMapping("api/Submarines")
    public List<SubmarineDTO> getSubmarines(){
        return submarineRepository.findAll().stream().map(SubmarineDTO::new).toList();
    }

    @PostMapping("api/Submarines")
    public ResponseEntity<?> postSubmarine(@RequestBody SubmarinePostPutDTO newSubmarine) {
        Submarine submarine = new Submarine();
        submarine.setName(newSubmarine.name);

        return ResponseEntity.status(HttpStatus.CREATED).body(submarineRepository.save(submarine));
    }

    @PutMapping("api/Submarines/{id}")
    public ResponseEntity<?> putSubmarine(@RequestBody SubmarinePostPutDTO newSubmarine, @PathVariable long id){
        Submarine submarine = submarineRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Submarine not found with id: " + id
                        )
                );

        submarine.setName(newSubmarine.name);
        submarineRepository.save(submarine);

        return ResponseEntity.status(HttpStatus.OK).body(new SubmarineDTO(submarine));
    }
}
