package com.andyron.bcdlj.c09;

import java.util.LinkedList;
import java.util.List;

/**
 * @author andyron
 **/
public class LinkedListTest {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("andy");
        list.add("jack");
        list.add("luck");

        System.out.println(list.get(2));
        System.out.println(list.indexOf("jack"));

    }
}
