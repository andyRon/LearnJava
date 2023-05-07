package ch02;

import java.util.concurrent.TimeUnit;

/**
 * t1
 * @author andyron
 **/
public class DaemonDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 开始运行，" +
                    (Thread.currentThread().isDaemon() ? "守护进程" : "用户线程"));
            while (true) {

            }
        }, "t1");

        t1.setDaemon(true);  // 设置成守护线程后，主线程（用户线程）结束后，t1也结束；如果t1也是用户线程，主线程结束后t1不会结束

        t1.start();

        // 暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName() + "\t -----end 主线程");

    }
}
