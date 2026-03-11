package top.andyron.ch13.ch136.ch1361;

public class Counter {
    private int count = 0;
    public void add(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public void dec(int n) {
        synchronized (this) {
            count -= n;
        }
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) {
        var c1 = new Counter();
        var c2 = new Counter();

        // 对c1进行操作的线程:
        new Thread(() -> {
            c1.add(2);
        }).start();
        new Thread(() -> {
            c1.dec(1);
        }).start();

        // 对c2进行操作的线程:
        new Thread(() -> {
            c2.add(3);
        }).start();
        new Thread(() -> {
            c2.dec(2);
        }).start();
    }
}
