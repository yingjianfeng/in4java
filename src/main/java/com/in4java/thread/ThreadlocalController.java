package com.in4java.thread;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadlocal")
public class ThreadlocalController {
    public static ThreadLocal<String> localVariable = new ThreadLocal<>();
    @GetMapping("/set")
    public String set(){
        localVariable.set("set");

        return "set:"+Thread.currentThread().getId();

    }

    @GetMapping("/get")
    public String get(){
        String res = localVariable.get();
        if(res!=null){
            System.out.println("get:"+res +"id:"+Thread.currentThread().getId());
        }
        return "get";
    }

}
