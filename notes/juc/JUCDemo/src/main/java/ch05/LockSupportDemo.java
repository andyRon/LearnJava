package ch05;

import java.util.concurrent.TimeUnit;

/**
 * 方式1（wait，notify）正常情况
 * @author andyron
 **/
public class LockSupportDemo {
    public static void main(String[] args) {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t -------come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t -------被唤醒");

            }
        }, "t1").start();

        // 暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t -------发出通知");
            }
        }, "t2").start();
    }
}
