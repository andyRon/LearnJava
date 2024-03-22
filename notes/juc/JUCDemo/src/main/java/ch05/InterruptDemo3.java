package ch05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过Thread类自带的中断API方法实现停止线程
 * @author andyron
 **/
public class InterruptDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t 中端标识被修改为ture，程序停止");
                    break;
                }
                System.out.println("t1 -------- hello 中断 api");
            }
        }, "t1");
        t1.start();

        // 暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
//        t1.interrupt(); // 自己停止自己
    }
}
