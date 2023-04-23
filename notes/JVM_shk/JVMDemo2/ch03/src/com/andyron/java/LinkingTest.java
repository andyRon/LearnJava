package com.andyron.java;

/**
 * 过程二：链接阶段
 * 基本数据类型：非final修饰的变量，在准备环节进行默认初始化赋值。
 *              final修饰，在准备环境直接进行显示赋值。
 *   拓展：如果使用字面量方式定义一个字符串的常量的话，也是在准备环境直接进行显示赋值。
 * @author andyron
 **/
public class LinkingTest {
    private static long id;
    private static final int num = 1;

    private static final String constStr = "CONST";
    private static final String constStr1 = new String("CONST");

    public void print1() {
        System.out.println("hello");
    }
}
