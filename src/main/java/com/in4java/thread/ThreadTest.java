package com.in4java.thread;

import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) {

        // 创建一个固定大小的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        Executor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        executor.execute(()->{
            System.out.println("xxx");
        });


    }
}
