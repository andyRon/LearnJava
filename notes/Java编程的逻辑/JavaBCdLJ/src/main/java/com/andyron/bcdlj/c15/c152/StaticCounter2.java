package com.andyron.bcdlj.c15.c152;

/**
 * @author andyron
 **/
public class StaticCounter2 {
    private static int count = 0;
    public static void incr() {
        synchronized (StaticCounter2.class) {
            count++;
        }

    }

    public static int getCount() {
        synchronized (StaticCounter2.class) {
            return count;
        }
    }
}
