package com.example.simplewatherapp.service;

import com.example.simplewatherapp.model.CurrentWeather;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Scanner;

@Service
public class WeatherService {

    @Value("${api.url}")
    private String BASE_URL;

    @Value("${api.key}")
    private String APP_ID;

    @Value("${api.city}")
    private String CITY;

    @Autowired
    private RestTemplate restTemplate;

    public CurrentWeather getCurrentWeather() {
        URI Url = new UriTemplate(BASE_URL).expand(CITY, APP_ID);
        return restTemplate.getForObject(Url,CurrentWeather.class);
    }


}
