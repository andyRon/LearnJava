package com.andyron.bcdlj.c07;

/**
 * @author andyron
 **/
public class IntegerTest {
    public static void main(String[] args) {
        int a = 0x12345678;
        System.out.println(Integer.toBinaryString(a));
        int r = Integer.reverse(a);
        System.out.println(Integer.toBinaryString(r));
        int rb = Integer.reverseBytes(a);
        System.out.println(Integer.toHexString(rb));

        System.out.println("----------------");
        int a1 = 0x12345678;
        int b = Integer.rotateLeft(a1, 8);
        System.out.println(Integer.toHexString(b));
        int c = Integer.rotateRight(a1, 8);
        System.out.println(Integer.toHexString(c));
    }
}
