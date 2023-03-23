package com.andyron.bcdlj.c15.c152;

/**
 * @author andyron
 **/
public class StaticCounterThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            StaticCounter.incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < num; i++) {
            threads[i] = new StaticCounterThread();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        System.out.println(StaticCounter.getCount());
    }
}
