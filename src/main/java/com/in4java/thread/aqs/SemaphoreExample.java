package com.in4java.thread.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: yingjf
 * @date: 2024/7/15 16:12
 * @description:
 */
public class SemaphoreExample {
    // 创建一个 Semaphore 对象，初始许可数量为 3
    private static final Semaphore semaphore = new Semaphore(3,true);

    public static void main(String[] args) {
        // 创建线程任务
        Runnable task = () -> {
            try {
                // 尝试获取一个许可，超时则抛出 InterruptedException
                if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                    try {
                        // 执行任务
                        System.out.println(Thread.currentThread().getName() + " acquired lock.");
                        // 模拟任务执行时间
                        Thread.sleep((long) (Math.random() * 5000));
                    } finally {
                        // 任务执行完毕后释放许可
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + " released lock.");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to acquire lock.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // 创建并启动 10 个线程执行任务
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task, "Thread-" + i);
            thread.start();
        }
    }
}