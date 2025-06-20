package com.in4java.thread;
import java.util.*;

public class ProducerConsumerExample {
    // 共享缓冲区
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final int BUFFER_SIZE = 5;

    public static void main(String[] args) {
        // 创建生产者线程
        Thread producerThread = new Thread(() -> {
            int product = 0;
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == BUFFER_SIZE) {
                        try {
                            System.out.println("Buffer is full. Producer is waiting...");
                            buffer.wait(); // 等待缓冲区有空间
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    buffer.add(product++);
                    System.out.println("Produced: " + (product - 1));
                    buffer.notifyAll(); // 通知消费者
                }
                try {
                    Thread.sleep((int) (Math.random() * 1000)); // 模拟生产时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建消费者线程
        Thread consumerThread = new Thread(() -> {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            System.out.println("Buffer is empty. Consumer is waiting...");
                            buffer.wait(); // 等待缓冲区有产品
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int product = buffer.poll();
                    System.out.println("Consumed: " + product);
                    buffer.notifyAll(); // 通知生产者
                }
                try {
                    Thread.sleep((int) (Math.random() * 1000)); // 模拟消费时间
                } catch (InterruptedException e)  {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        producerThread.start();
        consumerThread.start();
    }
}
