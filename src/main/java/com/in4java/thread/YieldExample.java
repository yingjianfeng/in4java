package com.in4java.thread;

public class YieldExample {
    public static void main(String[] args) {
        // 创建线程1
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 1: " + i);
                if (i % 2 == 0) {
                    Thread.yield(); // 每隔一次让出 CPU 时间片
                }
            }
        });

        // 创建线程2
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 2: " + i);
                if (i % 2 == 0) {
                    Thread.yield(); // 每隔一次让出 CPU 时间片
                }
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }
}
