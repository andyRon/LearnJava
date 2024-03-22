package ch05;

import java.util.concurrent.TimeUnit;

/**
 * 方式1（wait，notify）异常2：将notify放在wait方法前面。
 * 程序员无法执行，无法唤醒
 * @author andyron
 **/
public class LockSupportDemo3 {
    public static void main(String[] args) {
        Object objectLock = new Object();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
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


        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t -------发出通知");
            }
        }, "t2").start();
    }
}
