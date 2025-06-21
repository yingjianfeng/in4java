package com.in4java.thread.aqs;

/**
 * @author: yingjf
 * @date: 2024/7/8 13:41
 * @description:
 */
public class MyLockTest {
    static  int count = 0;
    static MySharedLock zyfLock = new MySharedLock();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    zyfLock.lockShared();
                    for (int i = 0; i < 10000; i++) {
                        count++;
                        System.out.println(Thread.currentThread().getName()+" "+count +" "+i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    zyfLock.unlockShared();
                }

            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //输入结果为20000
        System.out.println(count);

    }
}
