package com.andyron.bcdlj.c10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapTest {
    public static void main(String[] args) {
//        ex1();
//        ex2();
//        ex3();
//        ex4();

        ex5();
    }

    static void ex1() {
        // 1 默认按照ASCII从小到大排序
//        Map<String, String> map = new TreeMap<>();

        // 2 忽略大小写，String.CASE_INSENSITIVE_ORDER是一种比较器Comparator
//        Map<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        // 3 从大到小
//        Map<String, String> map = new TreeMap<>(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });
        // 从大到小另一种方式
//        Map<String, String> map = new TreeMap<>(Collections.reverseOrder());

        // 4 从大到小且忽略大小写
        Map<String, String> map = new TreeMap<>(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        map.put("b", "basic");
        map.put("a", "abstract");
        map.put("T", "tree");
        map.put("c", "call");
        for (Map.Entry<String, String> kv : map.entrySet()) {
            System.out.print(kv.getKey() + "=" + kv.getValue() + " ");
        }
    }

    static void ex2() {
        Map<String, String> map   = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        map.put("T", "tree");
        map.put("t", "try");
        for(Map.Entry<String, String> kv : map.entrySet()){
            System.out.print(kv.getKey()+"="+kv.getValue()+" ");
        }


    }

    static void ex3() {
        Map<String, Integer> map   = new TreeMap<>();
        map.put("2016-7-3", 100);
        map.put("2016-7-10", 120);
        map.put("2016-8-1", 90);
        for(Map.Entry<String, Integer> kv : map.entrySet()){
            System.out.println(kv.getKey()+", "+kv.getValue());
        }
    }

    static void ex4() {
        Map<String, Integer> map   = new TreeMap<>(new Comparator<String>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public int compare(String o1, String o2) {
                try {
                    return sdf.parse(o1).compareTo(sdf.parse(o2));
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        map.put("2016-7-3", 100);
        map.put("2016-7-10", 120);
        map.put("2016-8-1", 90);
        for(Map.Entry<String, Integer> kv : map.entrySet()){
            System.out.println(kv.getKey()+", "+kv.getValue());
        }
    }

    static void ex5() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("b", "basic");
        treeMap.put("a", "abstract");
        treeMap.put("T", "tree");
        treeMap.put("c", "call");

        System.out.println(treeMap);
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.subMap("a", "c"));
        System.out.println(treeMap.higherKey("b"));
        System.out.println(treeMap.lowerKey("b"));
        System.out.println(treeMap.lowerKey("Z"));
    }
}
