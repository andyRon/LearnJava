package com.andyron.bcdlj.c15.c153;

/**
 * 同时开始
 * @author andyron
 **/
public class Mian2 {
    public static void main(String[] args) throws InterruptedException {
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
    // 协作对象
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
    // 比赛运动员
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
}


