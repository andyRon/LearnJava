package com.andyron.bcdlj.c07;

public class Test {
    public static void main(String[] args) {
        Integer b = Integer.valueOf(100);

        Float f1 = 0.01f;
        Float f2 = 0.1f * 0.1f;
        System.out.println(f1.equals(f2));
        System.out.println(Float.floatToIntBits(f1));
        System.out.println(Float.floatToIntBits(f2));

        int a = 0x12345678;
        System.out.println(Integer.toBinaryString(a));
        int r = Integer.reverse(a);
        System.out.println(Integer.toBinaryString(r));
        int rb = Integer.reverseBytes(a);
        System.out.println(Integer.toHexString(rb));
    }
}
