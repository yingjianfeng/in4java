package com.in4java.base.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(finalI);
            }).start();
        }
        countDownLatch.await();
        System.out.println("执行结束");
    }


}
