package ch04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author andyron
 **/
public class ReEntryLockDemo {

    public static void main(String[] args) {
//        reentryM1();


        // 从头到尾只有t1这一个线程持有同一把锁
//        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
//        new Thread(() -> {
//            reEntryLockDemo.m1();
//        }, "t1").start();


        xianshiLock();
    }

    /**
     * 内部调用自己，能正常显示，没有死锁，说明是同一把锁
     * 同步块
     */
    private static void reentryM1() {
        final Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public synchronized void m1() {
        // 在一个synchronized修饰的方法或者代码块的内部调用本类的其他synchronized修饰的方法或代码块时,是永远可以得到锁的
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ------end m1");
    }
    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
        m3();
    }
    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
    }


    static Lock lock = new ReentrantLock();
    // 显示锁，lock和unlock方法是一一对应的。
    public static void xianshiLock() {

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----- come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----- come in内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
//                lock.unlock();
            }
        }, "t1").start();

        // 如果t1线程中缺少unlock，就会死锁，导致t2线程无法获取到锁，一直在等待
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----- come in外层调用");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
