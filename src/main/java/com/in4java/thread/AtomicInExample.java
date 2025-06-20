package com.in4java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInExample {
    private static int count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        AtomicInExample atomicInExample = new AtomicInExample();
        atomicInExample.atomicCount();
        atomicInExample.count();
    }

    public static void atomicCount() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                atomicCount.incrementAndGet();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final count: " + atomicCount.get());
    }

    public static void count() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
//                synchronized (AtomicInExample.class) {
                    count++;
//                }
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final count: " + count);
    }

}
