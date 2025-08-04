package com.in4java.spring.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitAObject {
    @Autowired
    private InitBObject initBObject;
    public InitAObject() {
        System.out.println("InitAObject 初始化啦");
    }
}
