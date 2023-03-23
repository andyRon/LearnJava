package com.andyron.bcdlj.c15.c153;

/**
 * 一个简单的生产者
 * Producer向共享队列中插入模拟的任务数据
 * @author andyron
 **/
public class Producer extends Thread {
    MyBlockingQueue<String> queue;

    public Producer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (true) {
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("produce task " + task);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {

        }
    }
}
