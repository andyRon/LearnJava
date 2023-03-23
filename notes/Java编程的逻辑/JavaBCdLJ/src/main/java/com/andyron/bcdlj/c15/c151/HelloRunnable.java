package com.andyron.bcdlj.c15.c151;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helloThread = new Thread(new HelloRunnable());
        helloThread.start();
        helloThread.join();
        System.out.println("world");
    }
}