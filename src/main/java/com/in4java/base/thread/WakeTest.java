package com.in4java.base.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WakeTest {

    public static void main(String[] args) throws Exception {
        WakeTest wakeTest = new WakeTest();
        wakeTest.lock();
        wakeTest.sync();
    }

    /**
     * 通过lock
     * @throws Exception
     */
    public void lock() throws Exception {
        Object obj = new Object();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread threadA = null;
        Thread threadB = null;
        threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + ">>>>" + i);
                    Thread.sleep(100);
                    condition.signal();
                    if (i != 9) condition.await();  // 防止最后时  还阻塞在这里
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }, "ThreadA");
        threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "<<<<" + i);
                    Thread.sleep(100);
                    condition.signal();
                    if (i != 9) condition.await();  // 防止最后时  还阻塞在这里
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }, "ThreadB");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();


    }
    /**
     * 通过synchronized
     * @throws Exception
     */
    public void sync() throws Exception {
        Object obj = new Object();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (obj) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ">>>>" + i);
                        Thread.sleep(100);
                        obj.notifyAll();
                        if (i != 9) obj.wait();  // 防止最后时  还阻塞在这里
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }, "ThreadA");
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (obj) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "<<<<" + i);
                        Thread.sleep(100);
                        obj.notifyAll();
                        if (i != 9) obj.wait();   // 防止最后时  还阻塞在这里
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "ThreadB");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
