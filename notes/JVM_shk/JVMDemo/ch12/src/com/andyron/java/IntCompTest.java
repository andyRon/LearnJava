package com.andyron.java;

/**
 * 测试解释器模式和JIT模式
 * -Xint    : 4174ms
 * -Xcomp   : 209ms
 * -Xmixed  : 246ms
 * @author andyron
 **/
public class IntCompTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        testPrimeNumber(1000000);

        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }
    private static void testPrimeNumber(int count) {
        for (int i = 0; i < count; i++) {
            // 计算100以内的质数 // TODO label
            label:
            for (int j = 2; j <= 100; j++) {
                for (int k = 2; k <= Math.sqrt(j); k++) {
                    if (j % k == 0) {
                        continue label;
                    }
                }
            }
        }
    }
}
