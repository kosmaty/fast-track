package com.example.subscriber;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by kyko on 9/15/2016.
 */
@Component
@Primary
@Profile("dev")
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

    public static MessagePrinter filePrinter(File file){
        FileMessagePrinter printer = new FileMessagePrinter();
        printer.setOutputFile(file);
        return printer;
    }

    public void printMessage(String source, String message) {
        System.out.println(prepareMessage(source, message));
    }

    protected String prepareMessage(String source, String message){
        return source + " <" + message + ">";
    }
}
