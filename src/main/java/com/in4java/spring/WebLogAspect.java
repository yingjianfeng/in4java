package com.in4java.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 统一日志处理切面
 * Created by 石磊
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {

    // 定义切入点
    @Pointcut("execution(public * com.in4java.spring.*.*(..))")
    public void webLog() {
    }

    // 方法执行前打印参数
    @Before("webLog() && @annotation(LogAnnotation)")
    public void doBefore(JoinPoint joinPoint) {
        // 获取方法签名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 获取方法参数名
        String[] parameterNames = methodSignature.getParameterNames();
        // 获取方法参数值
        Object[] args = joinPoint.getArgs();
        // 构建参数信息
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            params.append(parameterNames[i]).append(": ").append(args[i]);
            if (i < args.length - 1) {
                params.append(", ");
            }
        }
        // 打印方法执行前的日志
        log.info("方法执行前打印参数: [{}], Params: [{}]", method.getName(), params.toString());
    }

  /*  // 方法正常执行后打印返回值和参数
    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        // 获取方法签名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // 获取方法参数名
        String[] parameterNames = methodSignature.getParameterNames();
        // 获取方法参数值
        Object[] args = joinPoint.getArgs();

        // 构建参数信息
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            params.append(parameterNames[i]).append(": ").append(args[i]);
            if (i < args.length - 1) {
                params.append(", ");
            }
        }

        // 打印方法执行后的日志
        log.info("After method: [{}], Params: [{}], Return: [{}]", method.getName(), params.toString(), ret);
    }*/

    // 方法执行异常时打印异常信息和参数
    @AfterThrowing(value = "webLog() && @annotation(LogAnnotation) ", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        // 获取方法签名
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // 获取方法参数名
        String[] parameterNames = methodSignature.getParameterNames();
        // 获取方法参数值
        Object[] args = joinPoint.getArgs();

        // 构建参数信息
        StringBuilder params = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            params.append(parameterNames[i]).append(": ").append(args[i]);
            if (i < args.length - 1) {
                params.append(", ");
            }
        }

        // 打印方法异常时的日志
        log.error("方法执行异常前打印参数: [{}], Params: [{}], Exception: [{}]", method.getName(), params.toString(), ex.getMessage());
    }

/*    // 环绕通知（可选，用于记录方法执行时间等）
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method [{}] executed in [{}] ms", joinPoint.getSignature().getName(), (endTime - startTime));
        return result;
    }*/
}
