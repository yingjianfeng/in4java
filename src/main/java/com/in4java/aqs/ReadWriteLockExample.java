package com.in4java.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: yingjf
 * @date: 2024/7/15 17:26
 * @description:
 */
public class ReadWriteLockExample {
    private final List<String> sharedResource = new ArrayList<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void addElement(String element) {
        // 获取写锁
        rwLock.writeLock().lock();
        try {
            sharedResource.add(element);
            System.out.println("Added: " + element + ", Total: " + sharedResource.size());
        } finally {
            // 释放写锁
            rwLock.writeLock().unlock();
        }
    }

    public void printElements() {
        // 获取读锁
        rwLock.readLock().lock();
        try {
            System.out.println("Current elements: " + sharedResource);
        } finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockExample example = new ReadWriteLockExample();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 添加元素的任务
        Runnable addElementTask = () -> {
            int index = (int) (Math.random() * 100);
            example.addElement("Element-" + index);
        };

        // 打印元素的任务
        Runnable printElementsTask = () -> {
            example.printElements();
        };

        // 提交任务到线程池
        for (int i = 0; i < 50; i++) {
            executorService.submit(addElementTask);
            executorService.submit(printElementsTask);
        }

        executorService.shutdown();
    }
}
