package com.in4java;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class In4javaApplication {

    public static void main(String[] args) {
        SpringApplication.run(In4javaApplication.class, args);
    }

}



@Configuration
class TestServiceConfig implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("xxxx");
    }

    @Bean
    public Object test(){
        Object o = new Object();
        return o;
    }

}
