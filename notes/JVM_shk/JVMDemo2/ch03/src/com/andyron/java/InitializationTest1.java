package com.andyron.java;

/**
 * 哪些场景下，java编译器就不会生成<clinit>()方法?
 * @author andyron
 **/
public class InitializationTest1 {
    // 场景1：非静态字段，不管是否进行了显示赋值，不会生成<clinit>()
    public int num = 1;
    // 场景2：静态字段，没有显示的赋值，不会生成<clinit>()
    public static int num1;
    // 场景3：对于声明static final的基本数据类型的字段，不管是否进行了显示赋值，都不会生成<clinit>()方法
    public static final int num2 = 1;

}
