package com.andyron.bcdlj.c15;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hello");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread helloThread = new Thread(new HelloRunnable());
//        System.out.println(helloThread.getId());
//        System.out.println(helloThread.getState());
        helloThread.start();
        helloThread.join();
        System.out.println("world");
    }
}