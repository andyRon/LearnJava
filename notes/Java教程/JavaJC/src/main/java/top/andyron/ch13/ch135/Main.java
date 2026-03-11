package top.andyron.ch13.ch135;

public class Main {
    public static void main(String[] args) {
        TimerThread t = new TimerThread();
        t.setDaemon(true);
        t.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束，JVM推出");
    }
}
