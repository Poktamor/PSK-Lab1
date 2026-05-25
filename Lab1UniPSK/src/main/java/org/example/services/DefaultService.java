package org.example.services;

import org.example.interfaces.AlternativeExample;
import org.springframework.stereotype.Service;

@Service("defaultService")
public class DefaultService implements AlternativeExample {
    @Override
    public void sayHello(){
        System.out.println("Hello from Default");
    }
}
