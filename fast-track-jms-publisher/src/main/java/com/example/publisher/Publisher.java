package com.example.publisher;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

	@Autowired
	private JmsTemplate jmsTemplate;

	private Random random = new Random();
	private List<String> fruits = Arrays.asList("APPLE", "BANANA", "PEAR", "ANANAS", "PEACH");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@PostConstruct
	public void send() {
		for (int i = 0; i < 1000; i++) {

			String fruit = fruits.get(random.nextInt(fruits.size()));
			double price = random.nextInt(100);
			String text = fruit + "{price=" + price + ",time=" + dateFormat.format(new Date()) + "}";
			System.out.println("Sending an email message: " + text);
			jmsTemplate.send("test-queue", session -> {
				TextMessage message = session.createTextMessage(text);
				message.setStringProperty("type", fruit);
				return message;
			});

			waitASecond();
		}
	}

	private void waitASecond() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
