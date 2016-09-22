package com.example.web;

import com.example.core.FruitValuation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ValuationRepository {

    private List<FruitValuation> valuations = new CopyOnWriteArrayList<>();

    public void addValuation(FruitValuation valuation){
        valuations.add(valuation);
    }

    public Iterable<FruitValuation> allValuations(){
        return valuations;
    }
}
