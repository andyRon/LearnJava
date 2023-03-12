package com.andyron.bcdlj.c07.c76;

import java.util.Random;

/**
 * @author andyron
 **/
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Math.random());
        }

        Random rnd2 = new Random(20220101);
        for (int i = 0; i < 2; i++) {
            System.out.print(rnd2.nextInt(100) + " ");
        }
    }
}
