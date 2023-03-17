package com.andyron.bcdlj.c09;

import java.util.ArrayList;

/**
 * @author andyron
 **/
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("andy");
        strList.add("tom");
        strList.add("tom");
        strList.add("tom");
        strList.set(1, "jack");
        strList.add(1, "tony");
        System.out.println(strList);
        for (String s : strList) {
            System.out.println(s + '-' + s.length());
        }
        strList.trimToSize();
    }
}
