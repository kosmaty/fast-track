package com.example.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.core.Email;

@Component
public class Receiver {

	@JmsListener(destination = "test-queue", containerFactory = "durableFactory1", selector = "type = 'APPLE'")
	public void receiveDurableMessageWithSelector(String message) {
		System.out.println("Durable subscriber with selector <" + message + ">");
	}

	@JmsListener(destination = "test-queue", containerFactory = "durableFactory2")
	public void receiveDurableMessage(String message) {
		System.out.println("Durable subscriber <" + message + ">");
	}

	@JmsListener(destination = "test-queue", containerFactory = "notDurableTopicFactory")
	public void receiveNotDurableMessageFromTopic(String message) {
		System.out.println("Not durable subscriber <" + message + ">");
	}

	@JmsListener(destination = "test-queue", containerFactory = "queueFactory")
	public void receiveMessageFromQueue(String message) {
		System.out.println("Queue subscriber <" + message + ">");
	}

}
