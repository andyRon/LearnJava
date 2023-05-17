package com.andyron.java2;

/**
 * 同步省略说明
 * @author andyron
 **/
public class SysnchronizedTest {
    public void f() {
        Object hollis = new Object();
        synchronized (hollis) {
            System.out.println(hollis);
        }
    }
}
