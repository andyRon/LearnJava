package com.andyron.bcdlj.c15.c153;

/**
 * 协作对象
 * MyLatch是一个用于同步协作的工具类，主要用于演示基本原理，在Java中有一个专门的同步类CountDownLatch
 * @author andyron
 **/
public class MyLatch {
    private int count;

    public MyLatch(int count) {
        this.count = count;
    }
    public synchronized void await() throws InterruptedException {
        while (count > 0) {
            wait();
        }
    }
    public synchronized void countDown() {
        count--;
        if (count <= 0) {
            notifyAll();
        }
    }

}
