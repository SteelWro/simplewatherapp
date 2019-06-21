package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import com.example.simplewatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ScheduledWeatherImpl implements ScheduledWeather{

    private static final Logger log = LoggerFactory.getLogger(ScheduledWeather.class);

    WeatherService weatherService;
    CurrentWeather currentWeather;
    Writer output;
    File file = new File("log.txt");
    FileOutputStream fos;

    @Autowired
    public ScheduledWeatherImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void firstLineWriter() {
        currentWeather = weatherService.getCurrentWeather();

        try {
            fos = new FileOutputStream(file);
            output = new BufferedWriter(new FileWriter(file, false));
            output.write(currentWeather.firstLine());
            output.write(System.lineSeparator());
            output.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Scheduled(fixedDelay = 3600000)
    public void nextLinesScheduledWeatherWriter() {
        currentWeather = weatherService.getCurrentWeather();

        try {
            output = new BufferedWriter(new FileWriter(file, true));
            output.write(currentWeather.toString());
            output.write(System.lineSeparator());
            output.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
