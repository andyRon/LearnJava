package com.andyron.bcdlj.c15.c152;

public class StaticCounter {
    private static int count = 0;
    public static synchronized void incr() {
        count++;
    }
    public static synchronized int getCount() {
        return count;
    }
}