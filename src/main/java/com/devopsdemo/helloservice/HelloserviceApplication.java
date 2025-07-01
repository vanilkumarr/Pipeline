package com.devopsdemo.helloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class HelloserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloserviceApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from DevOps CI/CD pipeline!";
    }
}
