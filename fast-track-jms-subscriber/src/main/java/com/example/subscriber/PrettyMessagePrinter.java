package com.example.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pretty")
public class PrettyMessagePrinter extends  MessagePrinter {


    private String format = "The %s says %s";

    @Override
    protected String prepareMessage(String source, String message) {
        return String.format(format, source, message);
    }

    @Value("${printer.message.format}")
    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
