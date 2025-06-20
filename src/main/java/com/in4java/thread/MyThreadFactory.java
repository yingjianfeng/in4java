package com.in4java.thread;

import java.util.concurrent.*;

public class MyThreadFactory implements ThreadFactory {
    private final String namePrefix;

    public MyThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(namePrefix + "-" + thread.getId());
        return thread;
    }

    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
