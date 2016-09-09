package com.example.publisher;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class PublisherApplication {

	private static final List<String> fruits = Arrays.asList("APPLE", "BANANA", "PEAR", "ANANAS", "PEACH");

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(PublisherApplication.class, args);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		jmsTemplate.setPubSubDomain(true);
		Random random = new Random();
		// Send a message with a POJO - the template reuse the message converter
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

			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}