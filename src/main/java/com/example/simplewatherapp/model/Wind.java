package com.example.simplewatherapp.model;

import java.io.Serializable;

public class Wind {
    private long speed;
    private long deg;

    public Wind() {
    }

    public Wind(long speed, long deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getDeg() {
        return deg;
    }

    public void setDeg(long deg) {
        this.deg = deg;
    }
}
