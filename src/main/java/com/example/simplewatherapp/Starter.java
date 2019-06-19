package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class Starter{

    private static final Logger log = LoggerFactory.getLogger(ScheduledWeather.class);
    WeatherService weatherService;
    CurrentWeather currentWeather;
    LocalDateTime sunrise, sunset;

    @Autowired
    public Starter(WeatherService weatherService) {
        this.weatherService = weatherService;
        sunrise = null;
        sunset = null;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run() {
        currentWeather = weatherService.getCurrentWeather();
        long sunriseUNIX = currentWeather.getSys().getSunrise();
        long sunsetUNIX = currentWeather.getSys().getSunset();

        sunrise = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunriseUNIX), TimeZone.getDefault().toZoneId());
        sunset = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunsetUNIX), TimeZone.getDefault().toZoneId());

        String firstWeatherLog = String.format("%s %s",sunrise,sunset);
        log.info(firstWeatherLog);

    }
}
