package com.in4java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: yingjf
 * @date: 2025/3/24 13:58
 * @description:
 */
public class ThreadOrderWithLock {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();
    private static int turn = 1; // 1 表示线程1的回合，2 表示线程2的回合

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (turn != 1) { // 如果不是线程1的回合，等待
                        condition1.await();
                    }
                    System.out.println("Thread 1: " + i);
                    turn = 2; // 切换到线程2的回合
                    condition2.signal(); // 唤醒线程2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (turn != 2) { // 如果不是线程2的回合，等待
                        condition2.await();
                    }
                    System.out.println("Thread 2: " + i);
                    turn = 1; // 切换到线程1的回合
                    condition1.signal(); // 唤醒线程1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
