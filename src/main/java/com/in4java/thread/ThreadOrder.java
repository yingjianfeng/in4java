package com.in4java.thread;

/**
 * @author: yingjf
 * @date: 2025/3/24 13:53
 * @description:
 */
public class ThreadOrder {
    private static final Object lock = new Object();
    private static boolean turn = false; // 用于控制线程的轮流执行

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                synchronized (lock) {
                    while (turn) { // 如果不是线程1的回合，等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 1: " + i);
                    turn = true; // 切换到线程2的回合
                    lock.notify(); // 唤醒线程2
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                synchronized (lock) {
                    while (!turn) { // 如果不是线程2的回合，等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread 2: " + i);
                    turn = false; // 切换到线程1的回合
                    lock.notify(); // 唤醒线程1
                }
            }
        });

        t1.start();
        t2.start();
    }
}
