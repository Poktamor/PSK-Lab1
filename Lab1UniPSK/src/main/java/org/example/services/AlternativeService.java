package org.example.services;

import org.example.interfaces.AlternativeExample;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("alternativeService")
public class AlternativeService implements AlternativeExample {
    @Override
    public void sayHello(){
        System.out.println("Hello from Alternative");
    }
}
