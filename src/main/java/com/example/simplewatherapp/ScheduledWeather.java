package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Component
public class ScheduledWeather {

    private static final Logger log = LoggerFactory.getLogger(ScheduledWeather.class);
    private long iterator;

    WeatherService weatherService;
    CurrentWeather currentWeather;
    LocalDateTime localDateTime;

    @Autowired
    public ScheduledWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
        iterator = 1L;
    }

    @Scheduled(fixedDelay = 10000)
    public void printWeather() throws URISyntaxException {
        currentWeather = weatherService.getCurrentWeather();
        String WeatherLog = String.format("%s %s",currentWeather.getName(),currentWeather.getMain().getTemp());
        log.info(WeatherLog);
    }
}
