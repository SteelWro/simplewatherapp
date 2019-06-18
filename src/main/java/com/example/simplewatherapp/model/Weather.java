package com.example.simplewatherapp.model;

import java.io.Serializable;
import java.util.List;

public class Weather{

    private List<Weather> weather;

    public Weather() {
    }

    public Weather(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    //private long id;
//    private String main;
//    private String description;
//    private String icon;
//
//    public Weather() {
//    }
//
//    public Weather(long id, String main, String description, String icon) {
//        this.id = id;
//        this.main = main;
//        this.description = description;
//        this.icon = icon;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getMain() {
//        return main;
//    }
//
//    public void setMain(String main) {
//        this.main = main;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
    //    private long id;
//    private String main;
//    private String description;
//    private String icon;
//
//    public Weather() {
//    }
//
//    public Weather(long id, String main, String description, String icon) {
//        this.id = id;
//        this.main = main;
//        this.description = description;
//        this.icon = icon;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getMain() {
//        return main;
//    }
//
//    public void setMain(String main) {
//        this.main = main;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
}
