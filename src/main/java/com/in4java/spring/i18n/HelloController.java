package com.in4java.spring.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello/{message}")
    public String hello(HttpServletRequest request, @PathVariable String message) {
        String lang = request.getParameter("lang");
        System.out.println("lang: " + lang);
        return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }
}