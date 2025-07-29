package com.in4java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class In4javaApplication {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        //oomDump();
        SpringApplication.run(In4javaApplication.class, args);
    }

    /**
     * -Xms100M
     * -Xmx100M
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=/Users/apple/Desktop/oom
     */
    public static void oomDump(){
        List<Object> list = new ArrayList<>();
        while(true){
            list.add(new Object());
        }
    }
}


