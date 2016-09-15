package com.example.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by kyko on 9/15/2016.
 */
@Component
@Qualifier("pretty")
public class PrettyMessagePrinter extends  MessagePrinter {
    @Override
    public void printMessage(String source, String message) {
        System.out.println("The " + source + " says: " + message);
    }
}
