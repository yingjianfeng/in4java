package com.in4java.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private final Object target; // 被代理的对象

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法调用前的逻辑
        System.out.println("Before method: " + method.getName());
        long startTime = System.currentTimeMillis();

        // 调用实际的方法
        Object result = method.invoke(target, args);

        // 方法调用后的逻辑
        long endTime = System.currentTimeMillis();
        System.out.println("After method: " + method.getName() + ", took " + (endTime - startTime) + "ms");

        return result;
    }
}