package com.example.web;

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

    public void sendFruitValuationMessage(FruitValuation fv) {
        jmsTemplate.convertAndSend("test-queue", fv, message -> {
            message.setStringProperty("type", fv.getFruitType());
            return message;
        });

    }
}
