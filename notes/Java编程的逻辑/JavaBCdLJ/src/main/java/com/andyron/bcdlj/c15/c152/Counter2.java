package com.andyron.bcdlj.c15.c152;

/**
 * @author andyron
 **/
public class Counter2 {
    private int count;
    public void incr() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
