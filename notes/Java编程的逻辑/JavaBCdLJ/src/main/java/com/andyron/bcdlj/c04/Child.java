package com.andyron.bcdlj.c04;

public class Child extends Base{
    @Override
    public long sum(int a, long b) {
        System.out.println("child_int_long");
        return a + b;
    }
}
