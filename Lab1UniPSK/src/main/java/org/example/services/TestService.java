package org.example.services;

import org.example.interfaces.AlternativeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    @Qualifier("defaultService")
    AlternativeExample alternativeService;

    @Autowired
    NonSpecialService specialService;

    public void runTest(){
        alternativeService.sayHello();
        specialService.sayHello();
    }
}
