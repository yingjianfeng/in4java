package com.in4java.thread.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: yingjf
 * @date: 2024/7/8 13:41
 * @description:
 */
public class MyLock {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        /*@Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }*/
    }

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }
}
