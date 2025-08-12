package com.in4java.base.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏 控制同时阻塞在一个地方  然后同时运行
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "  "+ finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        System.out.println("over!!!");

    }
}
