package com.example.subscriber;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private ValuationsApplicationListener innerListener;

    public ApplicationStartupListener(ValuationsApplicationListener innerListener) {
        this.innerListener = innerListener;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        innerListener.handleContextStart();
    }
}
