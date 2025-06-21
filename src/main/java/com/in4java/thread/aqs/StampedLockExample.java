package com.in4java.thread.aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * @author: yingjf
 * @date: 2024/7/16 09:13
 * @description:
 */
public class StampedLockExample {
    private final StampedLock stampedLock = new StampedLock();
    private int counter = 0;

    public void increment() {
        long stamp = stampedLock.writeLock();
        try {
            // 写入操作
            counter++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public int read() {
        // 获取乐观锁 和 版本号
        long stamp = stampedLock.tryOptimisticRead();
        int result;
        try {
            // 读取操作
            result = counter;
        } finally {
            if (!stampedLock.validate(stamp)) {
                // 如果乐观读失败，尝试悲观读
                stamp = stampedLock.readLock();
                try {
                    result = counter;
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        StampedLockExample example = new StampedLockExample();

        // 启动一个线程来增加计数器
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                example.increment();
            }
        });

        // 启动一个线程来读取计数器
        Thread readThread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println("Counter value: " + example.read());
            }
        });

        incrementThread.start();
        readThread.start();

        try {
            incrementThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}