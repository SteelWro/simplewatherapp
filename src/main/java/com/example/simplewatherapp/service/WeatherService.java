package com.example.simplewatherapp.service;

import com.example.simplewatherapp.model.CurrentWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Scanner;

@Service
public class WeatherService {

    private Scanner scanner = new Scanner(System.in);

    @Value("${api.url}")
    private String BASE_URL;

    @Value("${api.key}")
    private String APP_ID;

    //@Value("${app.city}")
    private String CITY;

    public CurrentWeather getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate();
        URI currentWheatherUrl = new UriTemplate(BASE_URL).expand(CITY, APP_ID);
        return restTemplate.getForObject(currentWheatherUrl,CurrentWeather.class);
    }

    public void nameOfCity(){
        System.out.println("Give me name of city: ");
        CITY = scanner.next();
    }


}
