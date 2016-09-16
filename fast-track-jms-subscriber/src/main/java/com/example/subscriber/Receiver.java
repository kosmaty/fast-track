package com.example.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Component
public class Receiver {

    @Inject
    private MessagePrinter messagePrinter;

    @Inject
    @Qualifier("pretty")
    private MessagePrinter somePrettyMessagePrinter;

    @Resource(name = "prettyMessagePrinter")
    private MessagePrinter anotherPrettyPrinter;

    @PostConstruct
    public void sayHello(){
        somePrettyMessagePrinter.printMessage("Receiver", "Hello!");
    }

    public Receiver(MessagePrinter messagePrinter,
                    @Qualifier("pretty") MessagePrinter somePrettyMessagePrinter,
                    @Named("prettyMessagePrinter") MessagePrinter anotherPrettyPrinter) {
        this.messagePrinter = messagePrinter;
        this.somePrettyMessagePrinter = somePrettyMessagePrinter;
        this.anotherPrettyPrinter = anotherPrettyPrinter;
    }

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
