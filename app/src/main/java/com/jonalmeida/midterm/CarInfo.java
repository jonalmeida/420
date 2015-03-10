package com.jonalmeida.midterm;

import java.io.Serializable;

public class CarInfo implements Serializable {
    private String name, speed, model;
    private String dealerContact, price, financeRate, leaseRate;

    public CarInfo(String name, String speed, String model) {
        this.name = name;
        this.speed = speed;
        this.model = model;
    }

    public CarInfo(String name, String speed, String model,
                   String dealerContact, String price, String financeRate, String leaseRate) {
        this.name = name;
        this.speed = speed;
        this.model = model;
        this.dealerContact = dealerContact;
        this.price = price;
        this.financeRate = financeRate;
        this.leaseRate = leaseRate;
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
