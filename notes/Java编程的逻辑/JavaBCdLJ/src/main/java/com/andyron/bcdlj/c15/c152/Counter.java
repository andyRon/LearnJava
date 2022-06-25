package com.andyron.bcdlj.c15.c152;

public class Counter {
    private int count;
    public synchronized void incr() {
        count++;
    }
    public synchronized int getCount() {
        return count;
    }
}
