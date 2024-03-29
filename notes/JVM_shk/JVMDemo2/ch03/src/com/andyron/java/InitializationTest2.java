package com.andyron.java;

import java.util.Random;

/**
 * 说明：使用static + final修饰的字段的显示赋值操作，到底是在哪个阶段进行的赋值？
 *   情况1：在链接阶段的准备环节赋值
 *   情况2：在初始化阶段<clinit>()中赋值
 *
 * 结论：
 * 在链接阶段的准备环节赋值的情况：
 *   1. 对于基本数据类型的字段来说，如果static+final修饰，则显式赋值（直接赋值常量，而非调用方法）通常在链接阶段的准备环节进行
 *   2. 对于String来说，如果使用字面量的方式复制，使用static+final修饰，则显示赋值通常是在链接阶段的准备环境进行
 *
 * 最终结论：使用static+final修饰，且显示赋值中不涉及到方法或构造器调用的基本数据类型或String类型的显示赋值（字面量），是在链接阶段的准备环境进行。
 * @author andyron
 **/
public class InitializationTest2 {
    public static int a = 1; // 在初始化阶段<clinit>()中赋值
    public static final int INT_CONSTANT = 10; // 在链接阶段的准备环节赋值

    public static final Integer INTEGER_CONSTANT1 = Integer.valueOf(100); // 在初始化阶段<clinit>()中赋值
    public static Integer INTEGER_CONSTANT2 = Integer.valueOf(1000); // 在初始化阶段<clinit>()中赋值

    public static final String s0 = "helloworld0"; // 在链接阶段的准备环节赋值
    public static final String s1 = new String("helloworld1"); // 在初始化阶段<clinit>()中赋值

    public static String s2 = "helloworld2";

    public static final int NUM2 = new Random().nextInt(10); // 在初始化阶段<clinit>()中赋值
}
