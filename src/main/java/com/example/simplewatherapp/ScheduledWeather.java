package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
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
import java.util.TimeZone;

@Component
public class ScheduledWeather {

    private static final Logger log = LoggerFactory.getLogger(ScheduledWeather.class);

    @Value("${file.path}")
    String path;

    WeatherService weatherService;
    CurrentWeather currentWeather;
    LocalDateTime sunrise, sunset;
    Writer output = null;
    File file = new File(path);

    @Autowired
    public ScheduledWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
        sunrise = null;
        sunset = null;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void firstLineWriter() {
        currentWeather = weatherService.getCurrentWeather();
        long sunriseUNIX = currentWeather.getSys().getSunrise();
        long sunsetUNIX = currentWeather.getSys().getSunset();

        sunrise = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunriseUNIX * 1000), TimeZone.getTimeZone("7200").toZoneId());
        sunset = LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunsetUNIX * 1000), TimeZone.getTimeZone("7200").toZoneId());

        String firstWeatherLog = String.format("%s %s", sunrise, sunset);

        try {
            output = new BufferedWriter(new FileWriter(file, false));
            output.write(String.format("%s %s %s \n", currentWeather.getName(), sunrise.toLocalTime(), sunset.toLocalTime()));
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(firstWeatherLog);
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduledWeatherWriter() {
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
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info(weatherLog);
    }
}
