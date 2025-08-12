package com.in4java.base.aqs;

import java.util.concurrent.Semaphore;

/**
 * 信号量  控制同时访问的个数
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new  Thread(()->{

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  "+ semaphore.availablePermits());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                semaphore.release();
            }).start();
        }
        System.out.println("over!!!");
    }
}
