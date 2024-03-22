package ch05;

import java.util.concurrent.TimeUnit;

/**
 * 验证线程的中断标识为true，是不是就立刻停止
 * @author andyron
 **/
public class Interrupt {
    public static void main(String[] args) {
        // 实例方法interrupt()，仅仅是设置线程的中断状态未true，不会停止线程。
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 300; i++) {
                System.out.println("-----: " + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标识02： " + Thread.currentThread().isInterrupted());  // ture
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识： " + t1.isInterrupted());  // false

        try { TimeUnit.MILLISECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        t1.interrupt();  // true
        System.out.println("t1线程调用interrupt()后的中断标识01： " + t1.isInterrupted());  // true

        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("t1线程调用interrupt()后的中断标识03： " + t1.isInterrupted());  // false  因为2000ms后线程t1已经运行完了，是不活动线程
    }
}
