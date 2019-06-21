package com.example.simplewatherapp.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

public class Sys {

    private long type;
    private long id;
    private long message;
    private String country;
    private long sunrise;
    private long sunset;

    public Sys() {
    }

    public Sys(long type, long id, long message, String country, long sunrise, long sunset) {
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMessage() {
        return message;
    }

    public void setMessage(long message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalTime getSunrise() {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunrise * 1000), TimeZone.getTimeZone("UTC").toZoneId()).toLocalTime();
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(sunset * 1000), TimeZone.getTimeZone("UTC").toZoneId()).toLocalTime();
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
