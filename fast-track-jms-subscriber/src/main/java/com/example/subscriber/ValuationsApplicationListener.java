package com.example.subscriber;

import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Component
public class ValuationsApplicationListener {
    public ValuationsApplicationListener() {
        System.out.println("application listener created");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void handleContextStart(){
        System.out.println("application started");
    }

    @EventListener({ContextClosedEvent.class})
    public void handleContextStop(){
        System.out.println("application stopped");
    }
}
