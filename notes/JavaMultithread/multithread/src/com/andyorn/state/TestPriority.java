package com.andyorn.state;

/**
 🔖 结果有问题，优先级没有效果，每次都是代码的顺序执行？而且是一点变化都没有

 */
public class TestPriority {
    public static void main(String[] args) {
        // 主线程优先级，是个默认值，改不了
        System.out.println(Thread.currentThread().getName()+ "的优先级是" + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();

        Thread t1 = new Thread(myPriority, "线程1");
        Thread t2 = new Thread(myPriority, "线程2");
        Thread t3 = new Thread(myPriority, "线程3");
        Thread t4 = new Thread(myPriority, "线程4");
        Thread t5 = new Thread(myPriority, "线程5");
        Thread t6 = new Thread(myPriority, "线程6");
        Thread t7 = new Thread(myPriority, "线程7");

        // 先设置优先级再启动
        t1.start();

        t2.setPriority(1);
        t2.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        t3.setPriority(4);
        t3.start();

        t5.setPriority(8);
        t5.start();

        t6.setPriority(3);
        t6.start();


    }
}

class MyPriority implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "的优先级是" + Thread.currentThread().getPriority());
    }


}