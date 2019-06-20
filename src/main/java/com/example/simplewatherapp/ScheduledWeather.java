package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import com.example.simplewatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.TimeZone;

@Component
public class ScheduledWeather {

    private static final Logger log = LoggerFactory.getLogger(ScheduledWeather.class);

    WeatherService weatherService;
    CurrentWeather currentWeather;
    LocalDateTime sunrise, sunset;
    Writer output = null;
    File file = new File("result.txt");
    FileOutputStream fos;

    @Autowired
    public ScheduledWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
        sunrise = null;
        sunset = null;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void firstLineWriter() {
        weatherService.nameOfCity();
        currentWeather = weatherService.getCurrentWeather();
        long sunriseUNIX = currentWeather.getSys().getSunrise();
        long sunsetUNIX = currentWeather.getSys().getSunset();

        sunrise = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunriseUNIX * 1000), TimeZone.getTimeZone("UTC").toZoneId());
        sunset = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunsetUNIX * 1000), TimeZone.getTimeZone("UTC").toZoneId());

        String firstWeatherLog = String.format("%s %s %s %n", currentWeather.getName(), sunrise.toLocalTime(), sunset.toLocalTime(), System.lineSeparator());

        try {
            fos = new FileOutputStream(file);
            output = new BufferedWriter(new FileWriter(file, false));
            output.write(String.format("%s %s %s", currentWeather.getName(), sunrise.toLocalTime(), sunset.toLocalTime()));
            output.write(System.lineSeparator());
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(firstWeatherLog);
    }

    @Scheduled(fixedDelay = 3600000)
    public void nextLinesScheduledWeatherWriter() {
        currentWeather = weatherService.getCurrentWeather();
        String weatherLog = String.format("%s %s %s %s %s \n",
                LocalDateTime.now(),
                currentWeather.getMain().getTemp(),
                currentWeather.getMain().getHumidity(),
                currentWeather.getMain().getPressure(),
                currentWeather.getWind().getSpeed());

        try {
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(weatherLog);
            output.write(System.lineSeparator());
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(weatherLog);
    }
}
