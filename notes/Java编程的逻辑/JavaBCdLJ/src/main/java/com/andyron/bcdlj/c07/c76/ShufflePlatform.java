package com.andyron.bcdlj.c07.c76;

import java.util.Arrays;
import java.util.Random;

/**
 * @author andyron
 **/
public class ShufflePlatform {
    public static void main(String[] args) {
        int n = 15;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 将数组随机重排
     * @param arr
     */
    public static void shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length; i > 1; i--) {
            // 从后往前，逐个给每个数组位置重新赋值，值是从剩下的元素中随机挑选的。
            swap(arr, i-1, rnd.nextInt(i));
        }
    }
}
