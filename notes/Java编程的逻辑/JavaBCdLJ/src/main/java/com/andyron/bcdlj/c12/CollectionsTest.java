package com.andyron.bcdlj.c12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author andyron
 **/
public class CollectionsTest {
    public static void main(String[] args) {
        t1();
    }

    static void t1() {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{
                35, 24, 13, 12, 8, 7, 1
        }));
        System.out.println(Collections.binarySearch(list, 7, Collections.reverseOrder()));
    }
}
