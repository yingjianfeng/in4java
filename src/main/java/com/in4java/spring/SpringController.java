package com.in4java.spring;

import com.in4java.spring.exception.BusinessException;
import com.in4java.spring.log.LogAnnotation;
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


    @GetMapping("/ex/{id}")
    public String ex(@PathVariable Integer id) throws Exception{
        if(id<10) throw new BusinessException(100,"id<UNK>");
        else if (id >100) throw new Exception("id大于100");
        return "get";
    }

}
