package com.example.web;

import com.example.core.FruitValuation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private ValuationRepository repository;

    public Receiver(ValuationRepository repository) {
        this.repository = repository;
    }


    @JmsListener(destination = "test-queue", containerFactory = "queueFactory")
    public void receiveMessageFromQueue(FruitValuation valuation) {
        repository.addValuation(valuation);
    }

}
