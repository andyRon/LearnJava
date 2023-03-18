package com.andyron.bcdlj.c10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
//        TreeSet<String> words = new TreeSet<>();
//        words.addAll(Arrays.asList(new String[]{
//            "tree", "map", "hash", "map"
//        }));
//        for (String w : words) {
//            System.out.print(w + " ");
//        }


        Set<String> words = new TreeSet<String>(new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }});
        words.addAll(Arrays.asList(new String[]{
                "tree", "map", "hash", "Map",
        }));
        System.out.println(words);
    }

}
