package com.in4java.spring.eventpub.service;

import com.in4java.spring.eventpub.RegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GiftEvent {
        @EventListener
        public void GiftEvent(RegisterEvent registerEvent) {
            System.out.println(registerEvent.toString()+"   发送礼物！");
        }
    }

