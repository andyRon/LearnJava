package com.andyron.bcdlj.c15.c152;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 同步容器迭代问题
 * @author andyron
 **/
public class IterationTest {

    private static void startModifyThread(final List<String> list) {
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add("item " + i);
                    try {
                        Thread.sleep((int) (Math.random() * 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        modifyThread.start();
    }

    private static void startIteratorThread(final List<String> list) {
       Thread iteratorThread = new Thread(new Runnable() {
           @Override
           public void run() {
               while (true) {
                   for (String s : list) {

                   }
               }
           }
       });
       iteratorThread.start();
    }

    public static void main(String[] args) {
        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        startIteratorThread(list);
        startModifyThread(list);
    }
}
