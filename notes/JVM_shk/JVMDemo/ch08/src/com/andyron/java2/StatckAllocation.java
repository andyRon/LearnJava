package com.andyron.java2;

/**
 * 栈上分配测试
 * -Xms1G -Xmx1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 *
 * -Xms256m -Xmx256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * -Xms256m -Xmx256m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * @author andyron
 **/
public class StatckAllocation {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        try {
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void alloc() {
        User user = new User();
    }

    static class User {

    }
}
