package com.andyron.bcdlj.c15.c151;

/**
 * @author andyron
 **/
public class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("hello");
        System.out.println("thread name: " + Thread.currentThread().getName());
        System.out.println(this.getId());
        System.out.println(this.getName());
        System.out.println(this.getPriority());
        System.out.println(this.getState());
    }

    public static void main(String[] args) {
        HelloThread thread = new HelloThread();
        thread.start();
    }
}
