package com.example.web;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Date;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionData {
    private Date sessionStartDate;

    public SessionData() {
        sessionStartDate = new Date();
    }

    public Date getSessionStartDate() {
        return sessionStartDate;
    }
}
