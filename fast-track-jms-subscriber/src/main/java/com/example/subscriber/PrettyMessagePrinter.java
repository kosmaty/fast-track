package com.example.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kyko on 9/15/2016.
 */
@Component
@Qualifier("pretty")
public class PrettyMessagePrinter extends  MessagePrinter {


    private String format = "The %s says %s";

    @Override
    public void printMessage(String source, String message) {
        System.out.println(String.format(format, source, message));
    }

    @Value("${printer.message.format}")
    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
