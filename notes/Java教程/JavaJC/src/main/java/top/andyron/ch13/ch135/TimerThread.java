package top.andyron.ch13.ch135;

import java.time.LocalTime;

public class TimerThread extends  Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        TimerThread t = new TimerThread();
        t.start();
    }
}
