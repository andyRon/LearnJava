package com.andyron.java1;

/**
 * TLAB
 * 测试-XX:UseTLAB参数是否开启的情况：默认开启
 * @author andyron
 **/
public class TLABArgsTest {
    public static void main(String[] args) {
        System.out.println("------");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
