package com.example.simplewatherapp.controller;

import com.example.simplewatherapp.model.CurrentWeather;
import com.example.simplewatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class WeatherController {

    WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model){
        model.addAttribute("currentWeather", weatherService.getCurrentWeather());
        model.addAttribute("time", LocalDateTime.now());
    return "index";
    }
}
