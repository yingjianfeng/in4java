package com.in4java.spring.log;

import cn.hutool.core.lang.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final String traceId = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tid = UUID.randomUUID().toString().replace("-", "");
        //可以考虑让客户端传入链路ID，但需保证一定的复杂度唯一性；如果没使用默认UUID自动生成，这里也支持feign方式调用其他服务接口前提是要在请求头中把traceId带过去
        if (!StringUtils.isEmpty(request.getHeader("traceId"))){
            tid=request.getHeader("traceId");
        }
        MDC.put(traceId, tid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求处理完成后,清除MDC中的traceId,以免造成内存泄漏
        MDC.remove(traceId);
    }

}
