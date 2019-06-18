package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

@Component
public class ScheduledTasks {

    @Autowired
    WeatherService weatherService;

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    CurrentWeather currentWeather;


    @Scheduled(fixedDelay = 3000)
    public void printWeather() throws URISyntaxException {
        currentWeather = weatherService.getCurrentWeather();
        log.info(String.valueOf(currentWeather.getCoord().getLat()));
        log.info(String.valueOf(currentWeather.getCoord().getLon()));



    }
}
