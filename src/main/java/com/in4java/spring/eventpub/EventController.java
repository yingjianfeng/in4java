package com.in4java.spring.eventpub;

import com.in4java.spring.log.LogAnnotation;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/event")
public class EventController {
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    @GetMapping("/pub/{name}")
    public String set(@PathVariable String name) {
        applicationEventPublisher.publishEvent(new RegisterEvent(name));
        return "ok";
    }
}
