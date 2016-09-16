package com.example.publisher;

import com.example.core.FruitValuation;
import com.example.core.FruitValuations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Component
public class Publisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Unmarshaller unmarshaller;

    @Autowired
    private Marshaller marshaller;

    @Value("classpath:com/example/publisher/valuations.xml")
    private Resource valuationsSource;

    private Random random = new Random();
    private List<String> fruits = Arrays.asList("APPLE", "BANANA", "PEAR", "ANANAS", "PEACH");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private Iterator<FruitValuation> data;

    @PostConstruct
    public void initData() throws Exception {
        try (InputStream in = valuationsSource.getInputStream()) {
            FruitValuations valuations = (FruitValuations) unmarshaller.unmarshal(new StreamSource(in));
            data = valuations.getValuations().iterator();
        }
    }

    @Scheduled(fixedRate = 1000L)
    public void sendFruitValuationMessage() {
        FruitValuation fv = data.next();
        String text = String.format("%s {price=%f,time=%s",
                fv.getFruitType(), fv.getPrice(), dateFormat.format(fv.getTime()));
        System.out.println("Sending a message: " + text);
        jmsTemplate.send("test-queue", session -> {
            TextMessage message = session.createTextMessage(text);
            message.setStringProperty("type", fv.getFruitType());
            return message;
        });
    }
}
