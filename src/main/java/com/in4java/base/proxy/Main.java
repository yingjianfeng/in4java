package com.in4java.base.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        // 创建目标对象
        MyService target = new MyServiceImpl();

        // 创建 InvocationHandler
        MyInvocationHandler handler = new MyInvocationHandler(target);

        // 创建代理对象
        MyService proxy = (MyService) Proxy.newProxyInstance(
                MyService.class.getClassLoader(), // 目标类的类加载器
                new Class<?>[]{MyService.class}, // 目标类实现的接口
                handler // 自定义的 InvocationHandler
        );

        // 调用代理对象的方法
        proxy.doSomething();
    }
}
