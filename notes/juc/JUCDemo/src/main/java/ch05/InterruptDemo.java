package ch05;

import java.util.concurrent.TimeUnit;

/**
 * 通过volatile变量实现停止线程
 * @author andyron
 **/
public class InterruptDemo {
    static volatile boolean isStop = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为ture，程序停止");
                    break;
                }
                System.out.println("t1 -------- hello volatile");
            }
        }, "t1").start();

        // 暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();

    }
}
