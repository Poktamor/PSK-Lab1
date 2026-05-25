package org.example.controllers;

import org.example.services.LongTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class TestController {

    private final LongTaskService service;

    public TestController(LongTaskService service) {
        this.service = service;
    }

    @GetMapping("/api/start")
    public CompletableFuture<String> start(@RequestParam int x) {
        return service.doLongCalculation(x);
    }
}