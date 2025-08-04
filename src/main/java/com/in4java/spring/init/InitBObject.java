package com.in4java.spring.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitBObject {
    @Autowired
    private InitAObject initAObject;
    public InitBObject() {
        System.out.println("InitBObject 初始化啦");
    }
}
