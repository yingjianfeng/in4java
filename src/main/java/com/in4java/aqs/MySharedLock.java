package com.in4java.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: yingjf
 * @date: 2024/7/8 14:32
 * @description:
 */
public class MySharedLock {
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquires) {
            // 仅当状态为0时，才允许获取锁
            return compareAndSetState(0, acquires);
        }

        @Override
        protected boolean tryRelease(int releases) {
            // 减少同步状态
            setState(getState() - releases);
            // 如果状态为0，唤醒等待的线程
            if (getState() == 0) {
                releaseShared(0);
            }
            return true;
        }

        @Override
        protected int tryAcquireShared(int acquires) {
            for (; ; ) {
                int current = getState();
                int new_state = current + acquires;
                if (new_state < 0) {
                    // 如果新状态为负数，表示已经超出了int的最大值，返回负值
                    return -1;
                }
                if (compareAndSetState(current, new_state)) {
                    return 1;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int releases) {
            // 共享模式的释放逻辑
            for (; ; ) {
                int current = getState();
                int next = current - releases;
                if (next < 0) throw new IllegalMonitorStateException();
                boolean succeeded = compareAndSetState(current, next);
                if (succeeded || next == 0) {
                    return true;
                }
            }
        }
    }

    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public void lockShared() {
        sync.acquireShared(1);
    }

    public void unlockShared() {
        sync.releaseShared(1);
    }
}
