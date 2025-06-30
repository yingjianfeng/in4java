package com.in4java.spring.comfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
@WebFilter("/*")
@Order(1)
public class UrlCheckFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑
        log.info("过滤器UrlCheckFilter，初始化");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        StringBuffer url = httpRequest.getRequestURL();
        if(!url.toString().contains("127.0.0.1")){
            // 设置响应的内容类型为JSON
            httpResponse.setContentType("application/json;charset=UTF-8");
            // 创建JSON响应内容
            String jsonResponse = "{\"error\":\"非法请求！\"}";
            // 获取输出流并返回JSON响应
            PrintWriter out = httpResponse.getWriter();
            out.print(jsonResponse);
            out.flush();
            out.close();
            // 不再继续过滤器链，直接返回
            return;
        }
        chain.doFilter(request, response);
    }
}