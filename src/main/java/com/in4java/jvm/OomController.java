package com.in4java.jvm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/oom")
public class OomController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/save")
    public String save() throws InterruptedException {
        List<OomObj[]> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            OomObj[] arr = new OomObj[100];
            list.add(arr);
        }
        Thread.sleep(100000000);
        return "test";
    }

}
