package com.in4java.thread.cf;

import cn.hutool.core.date.DateTime;

/**
 * @author: yingjf
 * @date: 2024/7/25 09:17
 * @description:
 */
public class Task {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        System.out.println("a开始执行啦"+ DateTime.now());
        sleep(1000);
        System.out.println("            ->a执行完啦"+ DateTime.now());
    }

    public static void b() {
        System.out.println("b开始执行啦"+ DateTime.now());
        sleep(3000);
        System.out.println("            ->b执行完啦"+ DateTime.now());
    }

    public static void c() {
        System.out.println("c开始执行啦"+ DateTime.now());
        sleep(5000);
        System.out.println("            c执行完啦"+ DateTime.now());
    }

    public static void d() {
        System.out.println("d开始执行啦"+ DateTime.now());
        sleep(6000);
        System.out.println("            d执行完啦"+ DateTime.now());
    }

    public static void e() {
        System.out.println("e开始执行啦"+ DateTime.now());
        sleep(10000);
        System.out.println("            e执行完啦"+ DateTime.now());
    }


    public static void f() {
        System.out.println("f开始执行啦"+ DateTime.now());
        sleep(5000);
        System.out.println("            f执行完啦"+ DateTime.now());
    }

}
