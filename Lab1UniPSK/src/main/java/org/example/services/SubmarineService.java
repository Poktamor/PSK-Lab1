package org.example.services;

import org.example.DTOs.SubmarineDTO;
import org.example.DTOs.SubmarinePostPutDTO;
import org.example.models.Submarine;
import org.example.repositories.SubmarineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SubmarineService {
    private final SubmarineRepository submarineRepository;
    SubmarineService(SubmarineRepository SubmarineRepository) {
        this.submarineRepository = SubmarineRepository;
    }
    public ResponseEntity<?> getAll(){
        List<SubmarineDTO> submarines = submarineRepository.findAll().stream().map(SubmarineDTO::new).toList();
        if (submarines.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(submarines);
    }

    public ResponseEntity<?> addNew(SubmarinePostPutDTO newSubmarine){
        Submarine submarine = new Submarine();
        submarine.setName(newSubmarine.name);

        return ResponseEntity.status(HttpStatus.CREATED).body(submarineRepository.save(submarine));
    }

    public ResponseEntity<?> update(SubmarinePostPutDTO newSubmarine, Long id){
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
