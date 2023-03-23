package com.andyron.bcdlj.c15.c153;

/**
 * @author andyron
 **/
public class Main3 {
    public static void main(String[] args) throws InterruptedException {
        int workerNum = 100;
        MyLatch latch = new MyLatch(workerNum);
        Worker[] workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        latch.await();
        System.out.println("collect worker results");
    }


    static class Worker extends Thread {
        MyLatch latch;

        public Worker(MyLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) (Math.random() * 1000));
                this.latch.countDown();
            } catch (InterruptedException e) {

            }
        }
    }
}
