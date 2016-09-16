package com.example.publisher;

import com.example.core.FruitValuation;
import com.example.core.FruitValuations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class ValuationsGenerator {
    private static  Random random = new Random();
    private static List<String> fruits = Arrays.asList("APPLE", "BANANA", "PEAR", "ANANAS", "PEACH");

    public static void main(String[] args) throws Exception {
        FruitValuations fruitValuations = generateValuations();
        JAXBContext context = JAXBContext.newInstance(FruitValuations.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        m.marshal(fruitValuations, System.out);
    }

    private static FruitValuations generateValuations() {
        List<FruitValuation> valuations = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        LocalDateTime time = LocalDateTime.of(2016, Month.SEPTEMBER, 10, 14,0,0);
        for (int i = 0; i < 1000; i++) {

            String fruit = fruits.get(random.nextInt(fruits.size()));
            double price = random.nextInt(100);
            FruitValuation valuation = new FruitValuation(fruit, price,calendar.getTime());
            valuations.add(valuation);

            calendar.add(Calendar.SECOND, 1 );
        }
        FruitValuations fruitValuations = new FruitValuations();
        fruitValuations.setValuations(valuations);
        return fruitValuations;
    }

}
