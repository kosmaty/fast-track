package com.example.subscriber;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.core.Email;

@Component
public class Receiver {

//    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
//    public void receiveMessage(Email email) {
//        System.out.println("Received <" + email + ">");
//    }
    
    @JmsListener(destination = "test-queue", selector = "type = 'even'")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }

}
