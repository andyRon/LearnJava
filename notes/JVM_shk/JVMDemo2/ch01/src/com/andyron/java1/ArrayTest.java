package com.andyron.java1;

/**
 * @author andyron
 **/
public class ArrayTest {
    public static void main(String[] args) {
        Object[] arr = new Object[10];
        System.out.println(arr);  // [Ljava.lang.Object;@6bc7c054

        String[] arr1 = new String[10];
        System.out.println(arr1);  // [Ljava.lang.String;@232204a1

        long[][] arr2 = new long[10][];
        System.out.println(arr2);  // [[J@4aa298b7
    }
}
