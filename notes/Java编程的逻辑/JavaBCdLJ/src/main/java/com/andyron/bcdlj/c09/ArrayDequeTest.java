package com.andyron.bcdlj.c09;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author andyron
 **/
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add("andy");
        arrayDeque.add("john");
        arrayDeque.add("bill");
        arrayDeque.add("ann");
        System.out.println(arrayDeque.peek());
        arrayDeque.addAll(Arrays.asList("c", "cc", "ccc"));
        System.out.println(arrayDeque);

        arrayDeque.addFirst("a");
    }
}
