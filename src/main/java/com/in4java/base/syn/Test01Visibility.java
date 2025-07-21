package com.in4java.base.syn;

public class Test01Visibility {
    // 多个线程都会访问的数据，我们称为线程的共享数据
    private static volatile boolean run = true;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (run) {

            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(() -> {
            run = false;
            System.out.println("时间到，线程2设置为false");
        });
        t2.start();
    }
}
