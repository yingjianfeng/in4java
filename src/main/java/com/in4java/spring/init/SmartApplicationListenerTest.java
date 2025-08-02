package com.in4java.spring.init;

import com.in4java.spring.eventpub.RegisterEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SmartApplicationListenerTest implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass== RegisterEvent.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("SmartApplicationListenerTest>>>>>>>>"+applicationEvent.getSource());
    }
}
