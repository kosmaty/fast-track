package com.example.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.core.Email;

@Component
public class Receiver {

	@JmsListener(destination = "test-queue", containerFactory = "myFactory1", selector = "type = 'APPLE'")
	public void receiveMessageWithSelector(String message) {
		System.out.println("Receiver with selector <" + message + ">");
	}

	@JmsListener(destination = "test-queue", containerFactory = "myFactory2")
	public void receiveMessage(String message) {
		System.out.println("Receiver <" + message + ">");
	}

}
