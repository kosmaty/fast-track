package com.example.publisher;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.jms.TextMessage;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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


//    @Scheduled(fixedRate = 1000L)

    @PostConstruct
    public void initData() {
        URL resource = this.getClass().getResource("com/example/publisher/valuations.xml");

        try (InputStream in = valuationsSource.getInputStream()) {
            FruitValuations valuations = (FruitValuations) unmarshaller.unmarshal(new StreamSource(in));
            data = valuations.getValuations().iterator();

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (data.hasNext()) {
            sendFruitValuationMessage(data.next());
            waitASecond();
        }

    }

    public void sendFruitValuationMessage(FruitValuation fv) {
        String text = String.format("%s {price=%f,time=%s",
                fv.getFruitType(), fv.getPrice(), dateFormat.format(new Date()));
        System.out.println("Sending a message: " + text);
        jmsTemplate.send("test-queue", session -> {
            TextMessage message = session.createTextMessage(text);
            message.setStringProperty("type", fv.getFruitType());
            return message;
        });
    }

    private void waitASecond() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
