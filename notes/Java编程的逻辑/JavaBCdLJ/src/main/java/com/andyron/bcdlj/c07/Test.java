package com.andyron.bcdlj.c07;

public class Test {
    public static void main(String[] args) {
        Integer b = Integer.valueOf(100);

        Float f1 = 0.01f;
        Float f2 = 0.1f * 0.1f;
        System.out.println(f1.equals(f2));
        System.out.println(Float.floatToIntBits(f1));
        System.out.println(Float.floatToIntBits(f2));




    }
}
