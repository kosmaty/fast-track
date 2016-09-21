package com.example.subscriber;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;


public class ApplicationCloseListener implements ApplicationListener<ContextClosedEvent> {

    private ValuationsApplicationListener innerListener;

    public ApplicationCloseListener(ValuationsApplicationListener innerListener) {
        this.innerListener = innerListener;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        innerListener.handleContextStop();
    }
}
