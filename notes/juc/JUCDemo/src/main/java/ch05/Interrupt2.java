package ch05;

import java.util.concurrent.TimeUnit;

/**
 * 线程处于被阻塞状态时，在别的线程中调用当前线程对象的interrupt方法，那么线程将立即退出被阻塞状态，并抛出一个`InterruptedException`异常。
 * @author andyron
 **/
public class Interrupt2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t " + "中断标志位： " +
                            Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();   // 为什么要在异常处，再调用一次
                    e.printStackTrace();
                }

                System.out.println("----- hello Interrupt2");
            }
        }, "t1");
        t1.start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> t1.interrupt(), "t2").start();
    }
}
/*
    1 中断标志位默认为false；
    2 t2 向 t1发出中断协商（t2调用t1.interrupt()），中断标志位变为true；
    3 中断标识位true，正常情况，程序停止
    4 中断标识位true，异常情况，InterruptedException，将会把中断状态清楚，中断标志位变为false，导致无限循环
    5 在catch块中，需要再次给中断标识位设置为true，
 */
