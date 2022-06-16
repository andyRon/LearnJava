package com.andyron.bcdlj.c01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(Float.floatToIntBits(1.2345f)));
//        System.out.println(Long.toBinaryString(Double.doubleToLongBits(1.2345f)));
//        System.out.println((long) Math.pow(10, 1) - 1);

        Map<String, Long> map = new HashMap<>();
        Random rnd = new Random();
        System.out.println(Integer.MAX_VALUE);
        // 4294967296
        System.out.println(rnd.nextInt(Integer.MAX_VALUE));
    }

}
