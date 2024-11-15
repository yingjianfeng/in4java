package com.in4java.aqs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: yingjf
 * @date: 2024/7/22 18:53
 * @description:
 */
public class FutureThenAcceptTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("捡田螺的小男孩".equals(a)) {
                System.out.println("关注了");
            }

            System.out.println("先考虑考虑");
        });

        System.out.println(thenAcceptFuture.get());
    }
}
