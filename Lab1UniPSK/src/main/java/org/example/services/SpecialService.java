package org.example.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SpecialService extends NonSpecialService{
    public void sayHello() {
        System.out.println("Hello from Special service");
    }
}
