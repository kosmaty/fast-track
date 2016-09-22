package com.example.web;


import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Date;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestData {
    private Date requestTime = new Date();

    public Date getRequestTime() {
        return requestTime;
    }
}
