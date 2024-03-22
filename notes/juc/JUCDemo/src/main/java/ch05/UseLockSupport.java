package ch05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author andyron
 **/
public class UseLockSupport {

    public static void main(String[] args) {
        t2();
    }

    private static void t1() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
        }, "t1");
        t1.start();

        // 暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();
    }

    // 先唤醒后等待也可以
    private static void t2() {
        Thread t1 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in "  + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒 " + System.currentTimeMillis());
        }, "t1");
        t1.start();


        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知 "  + System.currentTimeMillis());
        }, "t2").start();
    }
}
