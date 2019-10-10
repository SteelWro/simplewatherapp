package com.example.simplewatherapp.controller;

import com.example.simplewatherapp.ScheduledWeatherLog;
import com.example.simplewatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
    WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model) {
        model.addAttribute("currentWeather", weatherService.getCurrentWeather());
        model.addAttribute("time", LocalDateTime.now());
        log.info("request weather attempt success");
        return "index";
    }
}
