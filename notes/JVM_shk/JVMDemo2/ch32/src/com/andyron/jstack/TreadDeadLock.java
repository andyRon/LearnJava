package com.andyron.jstack;

import java.util.Map;
import java.util.Set;

/**
 * 演示线程的死锁问题
 * @author andyron
 **/
public class TreadDeadLock {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append("1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append("3");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (s1) {
                        s1.append("d");
                        s2.append("4");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();



//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Map<Thread, StackTraceElement[]> all = Thread.getAllStackTraces();
//                Set<Map.Entry<Thread, StackTraceElement[]>> entries = all.entrySet();
//                for (Map.Entry<Thread, StackTraceElement[]> entry : entries) {
//                    Thread t = entry.getKey();
//                    StackTraceElement[] v = entry.getValue();
//                    System.out.println("【Thread name is : " + t.getName() + "】");
//                    for (StackTraceElement s : v) {
//                        System.out.println("\t" + s.toString());
//                    }
//                }
//            }
//        }).start();

    }
}
