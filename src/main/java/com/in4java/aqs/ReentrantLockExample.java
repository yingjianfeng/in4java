package com.in4java.aqs;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReentrantLockExample {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void lockMethod() {
        lock.lock();
        try {
            // 执行临界区代码
            System.out.println(Thread.currentThread().getName() + " is executing lockMethod.");
        } finally {
            lock.unlock();
        }
    }

    public void conditionMethod() throws InterruptedException {
        lock.lock();
        try {
            // 等待条件满足
            condition.await();
            // 执行临界区代码
            System.out.println(Thread.currentThread().getName() + " is executing conditionMethod.");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample example = new ReentrantLockExample();

        Thread thread1 = new Thread(() -> example.lockMethod(), "Thread 1");
        Thread thread2 = new Thread(() -> example.lockMethod(), "Thread 2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // 测试 condition 方法
        Thread conditionThread1 = new Thread(() -> {
            try {
                example.conditionMethod();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Condition Thread 1");

        Thread conditionThread2 = new Thread(() -> {
            try {
                example.conditionMethod();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Condition Thread 2");

        conditionThread1.start();
        conditionThread2.start();

        // 唤醒等待线程
        example.lock.lock();
        try {
            example.condition.signalAll();
        } finally {
            example.lock.unlock();
        }

        conditionThread1.join();
        conditionThread2.join();
    }
}