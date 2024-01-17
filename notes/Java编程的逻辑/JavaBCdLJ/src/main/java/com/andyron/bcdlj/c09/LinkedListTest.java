package com.andyron.bcdlj.c09;

import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    void descendingIterator() {
        Deque<String> deque = new LinkedList<>(Arrays.asList(new String[]{"a", "b", "c"}));
        Iterator<String> it = deque.descendingIterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
    }
}
