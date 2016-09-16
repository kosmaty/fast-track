package com.example.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FruitValuation {
    @XmlAttribute
    private String fruitType;
    @XmlAttribute
    private double price;
    @XmlAttribute
    private Date time;

    public FruitValuation() {
    }

    public FruitValuation(String fruitType, double price, Date time) {
        this.fruitType = fruitType;
        this.price = price;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
