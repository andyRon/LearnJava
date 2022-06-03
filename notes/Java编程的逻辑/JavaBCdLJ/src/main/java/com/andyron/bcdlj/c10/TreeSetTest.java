package com.andyron.bcdlj.c10;

import java.util.Arrays;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<String> words = new TreeSet<>();
        words.addAll(Arrays.asList(new String[]{
            "tree", "map", "hash", "map"
        }));
        for (String w : words) {
            System.out.print(w + " ");
        }
    }

}
