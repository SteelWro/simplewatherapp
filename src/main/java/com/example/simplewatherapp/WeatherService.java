package com.example.simplewatherapp;

import com.example.simplewatherapp.model.CurrentWeather;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class WeatherService {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=Wroclaw&APPID=e2544ee568ddfe551f1d63790dc8fcbb";
    //private final String APP_ID = "e2544ee568ddfe551f1d63790dc8fcbb";

   // private String country = "POLAND";
    //private String city = "Wroclaw";

    WeatherService(){

    }

    public CurrentWeather getCurrentWeather() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI currentWheatherUrl = new URI(BASE_URL);
        CurrentWeather currentWeather = restTemplate.getForObject(currentWheatherUrl,CurrentWeather.class);
        return currentWeather;
    }


}
