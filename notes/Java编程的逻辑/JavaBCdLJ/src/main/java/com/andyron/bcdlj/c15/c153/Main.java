package com.andyron.bcdlj.c15.c153;

/**
 * @author andyron
 **/
public class Main {
    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();

    }
}
