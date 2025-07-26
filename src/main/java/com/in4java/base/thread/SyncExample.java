package com.in4java.base.thread;

public class SyncExample {
    private int count = 0;

    public synchronized void increment() {
        count++; // 操作1
    }

    public synchronized int getCount() {
        return count; // 操作2
    }

    public static void main(String[] args) throws InterruptedException {
        SyncExample example = new SyncExample();

        // 线程1
        Thread t1 = new Thread(() -> {
            example.increment(); // 调用 increment 方法
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 线程2
        Thread t2 = new Thread(() -> {
            System.out.println(example.getCount()); // 调用 getCount 方法
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}