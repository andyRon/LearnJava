package com.andyron.bcdlj.c10;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashMapTest {
    public static void main(String[] args) {
        ex01();
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
