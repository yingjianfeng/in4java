package com.in4java.spring.init;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerTest implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Object source = applicationEvent.getSource();
        System.out.println("ApplicationListenerTest>>>>>>>>>"+source);
    }
}
