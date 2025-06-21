package com.in4java.thread.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: yingjf
 * @date: 2024/7/15 16:42
 * @description:
 */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        final int threadCount = 5; // 假设有5个子任务需要执行
        final CountDownLatch latch = new CountDownLatch(threadCount);

        // 创建并启动线程
        for (int i = 0; i < threadCount; i++) {
            int finalI = i; // 用于闭包，保证线程正确处理迭代变量
            new Thread(() -> {
                try {
                    // 模拟子任务执行时间
                    TimeUnit.SECONDS.sleep(finalI);
                    System.out.println("Thread " + finalI + " completed its task.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 每个线程完成任务后计数减1
                    latch.countDown();
                }
            }).start();
        }

        // 主线程等待所有子任务完成
        latch.await(2,TimeUnit.SECONDS); // 阻塞直到 CountDownLatch 的计数为 0
        System.out.println("All tasks completed, proceeding with other work.");
    }
}