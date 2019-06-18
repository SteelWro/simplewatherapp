package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import sun.awt.AWTAutoShutdown;

public class Starter implements CommandLineRunner {


    @Autowired
    WeatherService weatherService;

    @Override
    public void run(String... args) throws Exception {

    }
}
