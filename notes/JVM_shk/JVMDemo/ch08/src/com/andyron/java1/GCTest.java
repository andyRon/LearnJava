package com.andyron.java1;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Minor GC、Major GC、Full GC
 * -Xms9m -Xmx9m -XX:+PrintGCDetails
 *
 * @author andyron
 **/
public class GCTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "andyron.com";  // 字符串存储在堆空间
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为： " + i);
        }
    }
}
