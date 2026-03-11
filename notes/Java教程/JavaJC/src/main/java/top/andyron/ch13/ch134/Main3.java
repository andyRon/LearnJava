package top.andyron.ch13.ch134;

// 中断线程
public class Main3 {
    public static void main(String[] args)  throws InterruptedException {
        HelloThread3 t = new HelloThread3();
        t.start();
        Thread.sleep(1);  // 主线程暂停1毫秒，普通电脑，t线程中循环只能运行一次
//        Thread.sleep(10);
        t.running = false; // 标志位置为false
    }
}

class HelloThread3 extends Thread {
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
