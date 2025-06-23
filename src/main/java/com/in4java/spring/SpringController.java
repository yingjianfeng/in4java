package com.in4java.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class SpringController {
    public static ThreadLocal<String> localVariable = new ThreadLocal<>();

    @LogAnnotation
    @GetMapping("/set/{id}")
    public String set(@PathVariable String id) {
        localVariable.set("set"+id);
        return "set:"+Thread.currentThread().getId();
    }

//    @LogAnnotation
    @GetMapping("/get")
    public String get(){
        String res = localVariable.get();
        if(res!=null){
            System.out.println("get:"+res +"id:"+Thread.currentThread().getId());
        }
        return "get";
    }

}
