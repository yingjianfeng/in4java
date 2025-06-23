package com.in4java.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurationSupport {
    @Resource
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        //可以具体制定哪些需要拦截，哪些不拦截，其实也可以使用自定义注解更灵活完成
//                .addPathPatterns("/**")
//                .excludePathPatterns("/testxx.html");
    }
}
