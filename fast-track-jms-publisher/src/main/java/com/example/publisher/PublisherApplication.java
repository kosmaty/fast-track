package com.example.publisher;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
@EnableJms
public class PublisherApplication {

	@Bean
	public JmsTemplate myJmsTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

}