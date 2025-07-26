package com.in4java.base.aqs;

import java.util.concurrent.locks.LockSupport;

import static cn.hutool.poi.excel.sax.AttributeName.t;

public class LockSupportTest {
    public static void main(String[] args) {
        LockSupport.park(); // 阻塞当前线程
        Thread t = Thread.currentThread();
        LockSupport.unpark(t); // 唤醒指定线程

    }
}
