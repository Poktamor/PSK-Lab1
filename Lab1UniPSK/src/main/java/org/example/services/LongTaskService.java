package org.example.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class LongTaskService {

    @Async
    public CompletableFuture<String> doLongCalculation(int input) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int result = input * 2;

        return CompletableFuture.completedFuture("Result: " + result);
    }
}