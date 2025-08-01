package com.in4java;

import com.in4java.jvm.OomObj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class In4javaApplication {

    public static void main(String[] args) throws Exception{
        System.out.println(Arrays.toString(args));
        SpringApplication.run(In4javaApplication.class, args);
        //oomDump();
    }

    /**
     -Xms100M
     -Xmx100M
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=/Users/apple/Desktop/oom
     -XX:+PrintGCDetails
     -XX:+PrintGCTimeStamps
     -XX:+PrintHeapAtGC
     */
    public static void oomDump() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        while(true){
            OomObj[] arr = new OomObj[100];
            //Thread.sleep(1000);
            list.add(new Object());
           // System.out.println("xxx");
        }
    }
}


