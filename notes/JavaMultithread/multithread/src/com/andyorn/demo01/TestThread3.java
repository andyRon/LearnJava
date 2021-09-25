package com.andyorn.demo01;

/**
 * 创建线程方式2：实现Runnable接口
 * @author Andy Ron
 */
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        // 线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("Runnable我在看代码=----" + i);
        }
    }

    public static void main(String[] args) {
        // 创建Runnable接口的实现类
        TestThread3 testThread3 = new TestThread3();

        // 创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(testThread3).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Runnable我在学习多线程--" + i);
        }
    }
}
