package org.example.services;

import org.springframework.stereotype.Service;

@Service
public class NonSpecialService {
    public void sayHello(){
        System.out.println("Hello from non-special service");
    }
}
