package com.example.subscriber;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBaseSubscriberApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"main-context.xml"});
        context.registerShutdownHook();
    }
}
