package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class WeatherService {


    @Value("${api.url}")
    private String BASE_URL;

    @Value("${api.key}")
    private String APP_ID;

    @Value("${api.city}")
    private String CITY;

    public CurrentWeather getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate();
        URI currentWheatherUrl = new UriTemplate(BASE_URL).expand(CITY, APP_ID);
        return restTemplate.getForObject(currentWheatherUrl,CurrentWeather.class);
    }


}
