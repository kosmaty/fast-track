package com.example.subscriber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@Primary
@Profile("prod")
public class FileMessagePrinter extends MessagePrinter {


    private File outputFile;

    @Override
    public void printMessage(String source, String message) {
        String formattedMessage = prepareMessage(source, message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))){
            writer.write(formattedMessage);
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Value("${file.printer.output.location}")
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
}
