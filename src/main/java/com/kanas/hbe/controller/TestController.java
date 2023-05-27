package com.kanas.hbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/bye")
    public String goodbyeWorld() {
        return "Bye bye world!";
    }

}
