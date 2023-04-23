package com.andyron.java;

/**
 * 过程三：初始化阶段
 *
 * @author andyron
 **/
public class InitializationTest {
    public static int id = 1;
    public static int number;
    static {
        number = 2;
        System.out.println("father static{}");
    }
}
