package com.andyron.bcdlj.c16.c162;

import java.util.concurrent.locks.LockSupport;

/**
 * @author andyron
 **/
public class LockSupportDemo {
    /**
        主线程启动子线程t，线程t启动后调用park，放弃CPU，主线程睡眠1秒以确保子线程已执行LockSupport.park()，
        调用unpark，线程t恢复运行，输出exit。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                LockSupport.park(); // 放弃CPU
                System.out.println("exit");
            }
        };

        t.start();  // 启动子线程
        Thread.sleep(1000);

        LockSupport.unpark(t);
    }
}
