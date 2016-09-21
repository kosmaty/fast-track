package com.example.web;

import com.example.core.FruitValuation;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ValuationRepository {

    private List<FruitValuation> valuations = new ArrayList<>();

    public void addValuation(FruitValuation valuation){
        valuations.add(valuation);
    }

    public Iterable<FruitValuation> allValuations(){
        return valuations;
    }
}
