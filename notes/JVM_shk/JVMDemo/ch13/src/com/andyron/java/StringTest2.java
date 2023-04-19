package com.andyron.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 测试设置字符串常量池（Hashtable（数组加链表））的长度
 * -XX:StringTableSize=1009
 * -XX:StringTableSize=100009
 *
 * 长度太小时，就会出现Hash冲突，出现多个字符串在同一个数组位置上，而形成链表；
 * 从而导致查询时遍历链表，花费更长的时间。
 *
 * `jinfo -flag StringTableSize pid`
 * @author andyron
 **/
public class StringTest2 {
    public static void main(String[] args) {
//        System.out.println("我是打酱油的");
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("word.txt"));
            long start = System.currentTimeMillis();
            String data;
            while ((data = br.readLine()) != null) {
                data.intern();  // intern表示如果字符串常量中没有对应的data字符串的话，就在生成
            }
            long end = System.currentTimeMillis();

            System.out.println("花费的时间为：" + (end - start)); // 1009: 141ms, 100009: 42ms。
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
