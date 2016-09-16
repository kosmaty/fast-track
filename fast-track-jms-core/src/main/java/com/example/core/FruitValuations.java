package com.example.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FruitValuations {
    @XmlElement(name = "fv")
    private List<FruitValuation> valuations = new ArrayList<>();
    public List<FruitValuation> getValuations() {
        return valuations;
    }

    public void setValuations(List<FruitValuation> valuations) {
        this.valuations = valuations;
    }

}
