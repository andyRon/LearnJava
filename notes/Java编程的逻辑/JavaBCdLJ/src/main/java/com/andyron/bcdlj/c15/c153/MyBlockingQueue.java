package com.andyron.bcdlj.c15.c153;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 生产者/消费者协作队列
 * @author andyron
 **/
public class MyBlockingQueue<E> {
    private Queue<E> queue = null;
    private int limit;
    public MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new ArrayDeque<>(limit);
    }
    // put是给生产者使用的，往队列上放数据，满了就wait，放完之后调用notifyAll，通知可能的消费者。
    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }
    // take是给消费者使用的，从队列中取数据，如果为空就wait，取完之后调用notifyAll，通知可能的生产者。
    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E e = queue.poll();
        notifyAll();
        return e;
    }
}
