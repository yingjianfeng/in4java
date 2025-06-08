package com.in4java.base.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: yingjf
 * @date: 2025/6/7 22:36
 * @description:
 */
public class CallableTest {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Object> future = service.submit(() -> new Random().nextLong());
        System.out.println(future.get());
        service.shutdown();

        new Thread(()->{System.out.println("xxxxxxxxxx");}).start();



    }

}


