package com.example.simplewatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SimplewatherappApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimplewatherappApplication.class, args);
    }
}
