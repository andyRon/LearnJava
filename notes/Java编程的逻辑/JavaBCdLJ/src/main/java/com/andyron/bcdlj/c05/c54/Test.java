package com.andyron.bcdlj.c05.c54;

public class Test {
    public static void main(String[] args) {
        Size size = Size.MEDIUM;
        System.out.println(size.toString());
        System.out.println(size.name());
        System.out.println(size.ordinal());
        for (Size s : Size.values()) {
            System.out.println(s);
        }
    }
}
