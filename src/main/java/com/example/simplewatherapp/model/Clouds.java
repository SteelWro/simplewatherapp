package com.example.simplewatherapp.model;

import java.io.Serializable;

public class Clouds{
    private long all;

    public Clouds() {
    }

    public Clouds(long all) {
        this.all = all;
    }

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }
}
