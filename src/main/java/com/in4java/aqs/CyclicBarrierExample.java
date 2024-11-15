package com.in4java.aqs;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: yingjf
 * @date: 2024/7/15 16:52
 * @description:
 */
public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int numberOfThreads = 5;
        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            System.out.println("所有线程已到达屏障点，继续执行后续操作...");
        });

        for (int i = 0; i < 10; i++) {
            int finalI = i; // 用于闭包，保证线程正确处理迭代变量
            new Thread(() -> {
                try {
                    // 模拟线程工作
                    System.out.println("线程 " + finalI + " 正在执行任务...");
                    //TimeUnit.SECONDS.sleep(1); // 假设每个线程需要1秒来完成其任务

                    // 当到达屏障点时，等待其他线程
                    barrier.await();

                    // 所有线程都到达屏障点后执行
                    System.out.println("线程 " + finalI + " 通过了屏障点，准备执行后续任务。");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
