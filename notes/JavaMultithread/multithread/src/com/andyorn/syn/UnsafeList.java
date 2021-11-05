package com.andyorn.syn;

import java.util.ArrayList;
import java.util.List;

/**
    线程不安全的集合
    list结果可能小于10000，是因为add时的两个线程同一时间可能添加到同一位置，而覆盖了
    🔖可能因为系统问题，Mac系统结果每次都是10000
 */
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }

}
