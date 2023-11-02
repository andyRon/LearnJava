package com.andyron.bcdlj.c10;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashMapTest {
    public static void main(String[] args) {
//        ex01();
//        Map<String, Integer> countMap = new HashMap<>();
//        countMap.put("hello", 1);
//        countMap.put("world", 3);
//        countMap.put("position", 4);
//        System.out.println("hello".hashCode());
//        System.out.println(hash7("hello"));
//        System.out.println(hash("hello"));


    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    // jdk7的HashMap的hash
    static final int hash7(Object k) {
        int h = 0;
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 随机产生1000个0～3的数，统计每个数的次数
     */
    static void ex01() {
        Random rnd = new Random();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int num = rnd.nextInt(4);
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, count + 1);
            }
        }

        for (Map.Entry<Integer, Integer> kv : countMap.entrySet()) {
            System.out.println(kv.getKey() + ", " + kv.getValue());
        }
    }
}
