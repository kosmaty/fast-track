package com.example.subscriber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBaseSubscriberApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "prod");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"main-context.xml"});
        context.registerShutdownHook();
    }
}
