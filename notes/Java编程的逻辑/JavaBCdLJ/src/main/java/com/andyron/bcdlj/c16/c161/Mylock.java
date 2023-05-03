package com.andyron.bcdlj.c16.c161;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger实现锁
 * status表示锁的状态，0表示未锁定，1表示锁定，lock()、unlock()使用CAS方法更新，lock()只有在更新成功后才退出，实现了阻塞的效果
 */
public class Mylock {
    private AtomicInteger status = new AtomicInteger(0);
    public void lock() {
        while (!status.compareAndSet(0, 1)) {
            Thread.yield();
        }
    }
    public void unlock() {
        status.compareAndSet(1, 0);
    }
}
