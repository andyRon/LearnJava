package top.andyron.ch13.ch133;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("hello");
        });
        System.out.println("start");
        t.start();
        t.join(); // 此处main线程会等待t结束
        System.out.println("end");
    }
}
