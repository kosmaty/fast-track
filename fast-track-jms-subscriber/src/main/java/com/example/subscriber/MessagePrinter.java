package com.example.subscriber;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by kyko on 9/15/2016.
 */
@Component
@Primary
public class MessagePrinter {
    private String prefix = "";

    public static MessagePrinter simplePrinter(){
        return new MessagePrinter();
    }

    public static MessagePrinter prettyPrinter(){
        return new PrettyMessagePrinter();
    }

    public static MessagePrinter prettyPrinter(String format){
        PrettyMessagePrinter printer = new PrettyMessagePrinter();
        printer.setFormat(format);
        return printer;
    }

    public void printMessage(String source, String message) {
        System.out.println(source + " <" + message + ">");
    }
}
