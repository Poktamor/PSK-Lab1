package org.example.controllers;

import org.example.DTOs.SubmarinePostPutDTO;
import org.example.services.SubmarineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubmarineController {
    private final SubmarineService submarineService;

    SubmarineController(SubmarineService SubmarineService) {
        this.submarineService = SubmarineService;
    }

    @GetMapping("api/Submarines")
    public ResponseEntity<?> getSubmarines(){
        return submarineService.getAll();
    }

    @PostMapping("api/Submarines")
    public ResponseEntity<?> postSubmarine(@RequestBody SubmarinePostPutDTO newSubmarine) {
        return submarineService.addNew(newSubmarine);
    }

    @PutMapping("api/Submarines/{id}")
    public ResponseEntity<?> putSubmarine(@RequestBody SubmarinePostPutDTO newSubmarine, @PathVariable long id){
        return submarineService.update(newSubmarine, id);
    }
}
