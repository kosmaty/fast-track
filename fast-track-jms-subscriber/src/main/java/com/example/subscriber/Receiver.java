package com.example.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

@Component
public class Receiver {

    @Inject
    private MessagePrinter messagePrinter;

    @Inject
    @Qualifier("pretty")
    private MessagePrinter somePrettyMessagePrinter;

    @Resource(name = "prettyMessagePrinter")
    private MessagePrinter anotherPrettyPrinter;

    @JmsListener(destination = "test-queue", containerFactory = "durableFactory1", selector = "type = 'APPLE'")
    public void receiveDurableMessageWithSelector(String message) {
        messagePrinter.printMessage("Durable subscriber with selector", message);
    }

    @JmsListener(destination = "test-queue", containerFactory = "durableFactory2")
    public void receiveDurableMessage(String message) {
        somePrettyMessagePrinter.printMessage("Durable subscriber", message);
    }

    @JmsListener(destination = "test-queue", containerFactory = "notDurableTopicFactory")
    public void receiveNotDurableMessageFromTopic(String message) {
        messagePrinter.printMessage("Not durable subscriber", message);
    }

    @JmsListener(destination = "test-queue", containerFactory = "queueFactory")
    public void receiveMessageFromQueue(String message) {
        anotherPrettyPrinter.printMessage("Queue subscriber", message);
    }

}
