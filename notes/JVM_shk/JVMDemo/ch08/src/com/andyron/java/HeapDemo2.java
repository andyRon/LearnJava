package com.andyron.java;

/**
 * -Xms20m -Xmx20m
 * @author andyron
 **/
public class HeapDemo2 {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }
}
