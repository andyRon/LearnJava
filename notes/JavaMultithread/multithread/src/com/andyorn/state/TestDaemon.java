package com.andyorn.state;

/**
 结果出现goodbye后可能还会出现一些上帝保佑你，这是因为虚拟机关闭需要一定的时间
 */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true); // 默认是false，代表用户进程，正常的线程都是用户进程

        thread.start();  // 上帝 守护进程开启

        new Thread(you).start();  // 你，用户进程开启
    }
}

// 上帝
class God implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保佑着你");
        }
    }
}

// 你
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 35600; i++) {
            System.out.println("每天都开心");
        }
        System.out.println("=====goodbye World========");
    }
}