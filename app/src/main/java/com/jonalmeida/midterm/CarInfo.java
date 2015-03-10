package com.jonalmeida.midterm;

import java.io.Serializable;

public class CarInfo implements Serializable {
    private String name, speed, model;

    public CarInfo(String name, String speed, String model) {
        this.name = name;
        this.speed = speed;
        this.model = model;
    }

    public String getSpeed() {
        return speed;
    }


    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

}
