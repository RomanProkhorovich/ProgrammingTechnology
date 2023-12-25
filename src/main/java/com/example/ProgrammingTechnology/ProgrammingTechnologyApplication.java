package com.example.ProgrammingTechnology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity


public class ProgrammingTechnologyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgrammingTechnologyApplication.class, args);
    }

}
