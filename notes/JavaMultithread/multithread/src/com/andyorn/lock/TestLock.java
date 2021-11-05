package com.andyorn.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Andy Ron
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 lock2 = new TestLock2();

        new Thread(lock2).start();
        new Thread(lock2).start();
        new Thread(lock2).start();
    }
}

class TestLock2 implements Runnable {

    int ticketName = 10;

    // 定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();  // 加锁
            try {
                if (ticketName > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketName--);
                } else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }



        }
    }
}
