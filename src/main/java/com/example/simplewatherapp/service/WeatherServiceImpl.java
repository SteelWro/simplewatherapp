package com.example.simplewatherapp.service;

import com.example.simplewatherapp.controller.WeatherController;
import com.example.simplewatherapp.model.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class WeatherServiceImpl implements WeatherService{
    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    @Value("${api.url}")
    private String BASE_URL;

    @Value("${api.key}")
    private String APP_ID;

    @Value("${api.city}")
    private String CITY;

    @Autowired
    private RestTemplate restTemplate;

    public CurrentWeather getCurrentWeather() {
        CurrentWeather currentWeather = null;
        URI Url = new UriTemplate(BASE_URL).expand(CITY, APP_ID);
        try {
            currentWeather = restTemplate.getForObject(Url, CurrentWeather.class);
        }catch (Exception e){
            log.error("Request weather attempt failed: "+ e);
        }
        return currentWeather;
    }
}
