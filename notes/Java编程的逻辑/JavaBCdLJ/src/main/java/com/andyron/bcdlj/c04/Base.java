package com.andyron.bcdlj.c04;

public class Base {
    public long sum(int a, long b) {
        System.out.println("base_int_long");
        return a + b;
    }

    public static void main(String[] args) {
        Child child = new Child();
        int a = 2;
        int b = 3;
        child.sum(2, 3);
    }
}
