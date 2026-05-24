package com.aniket.quiz.quizmachine_by_aniket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello! Spring Boot is working";
    }
}