package com.example.core;

/**
 * Created by kyko on 9/15/2016.
 */
public class FruitValuation {
    private String fruitType;
    private double price;

    public FruitValuation() {
    }

    public FruitValuation(String fruitType, double price) {
        this.fruitType = fruitType;
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFruitType() {
        return fruitType;
    }

    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }
}
