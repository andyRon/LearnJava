package com.andyron.java1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m
 * @author andyron
 **/
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 1024)];

    public static void main(String[] args) {
        ArrayList<HeapInstanceTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
