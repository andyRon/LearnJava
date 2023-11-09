package com.andyron.bcdlj.c04.c442;

/**
 * @author andyron
 **/
public class Base {
    private static final int MAX_NUM = 1000;
    private int[] arr = new int[MAX_NUM];
    private int count;

    public void add(int number) {
        if (count < MAX_NUM) {
            arr[count++] = number;
        }
    }
    public void addAll(int[] numbers) {
        for (int num : numbers) {
            add(num);
        }
    }
}
