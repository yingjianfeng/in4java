package com.in4java.spring.comfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
@WebFilter("/*")
@Order(2)
public class UrlTimeFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑
        log.info("过滤器UrlTimeFilter，初始化");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 获取完整的请求URL
        StringBuffer url = httpRequest.getRequestURL();
        //log.info("过滤器，开始执行，拦截到：【{}】",url);
        chain.doFilter(request, response); // 放行
        stopWatch.stop();
        log.info("过滤器，结束执行，拦截到：【{}】,耗时：【{}秒】",url,String.format("%.6f秒", stopWatch.getTotalTimeSeconds()));
    }
}