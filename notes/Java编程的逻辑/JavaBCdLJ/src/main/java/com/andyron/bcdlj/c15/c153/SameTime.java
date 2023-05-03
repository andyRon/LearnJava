package com.andyron.bcdlj.c15.c153;

/**
 * 同时开始
 * 有一个主线程和N个子线程，每个子线程模拟一个运动员，主线程模拟裁判，它们协作的共享变量是一个开始信号
 * @author andyron
 **/
public class SameTime {
    /**
     * 协作对象
     * 子线程应该调用waitForFire()等待枪响，而主线程应该调用fire()发射比赛开始信号。
     */
    static class FireFlag {
        private volatile boolean fired = false;
        public synchronized void waitForFire() throws InterruptedException {
            while (!fired) {
                wait();
            }
        }
        public synchronized void fire() {
            this.fired = true;
            notifyAll();
        }
    }

    /**
     * 比赛运动员
     */
    static class Racer extends Thread {
        FireFlag fireFlag;
        public Racer(FireFlag fireFlag) {
            this.fireFlag = fireFlag;
        }

        @Override
        public void run() {
            try {
                this.fireFlag.waitForFire();
                System.out.println("start run " + Thread.currentThread().getName());
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动了10个子线程，每个子线程启动后等待fire信号，主线程调用fire()后各个子线程才开始执行后续操作
        int num = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        Thread.sleep(1000);
        fireFlag.fire();
    }
}
