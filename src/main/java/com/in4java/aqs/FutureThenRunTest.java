package com.in4java.aqs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yingjf
 * @date: 2024/7/22 18:47
 * @description:
 */
public class FutureThenRunTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> orgFuture = CompletableFuture.runAsync(
                ()->{
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("先执行第一个CompletableFuture方法任务");
                }
                ,executorService );

        CompletableFuture thenRunFuture = orgFuture.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println("接着执行第二个任务");
        },executorService);

        System.out.println(thenRunFuture.get());
    }
}
