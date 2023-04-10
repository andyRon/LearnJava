package com.andyron.java;

/**
 * @author andyron
 **/
public class ClassInitTest {
    private static int num = 1;

    static {
        num = 2;
        number = 20;
        System.out.println(num);
//        System.out.println(number); // 报错，非法的前向引用。（因为声明变量在后面，在静态块里可以赋值，不可以调用）
    }
    // linking的prepare：number=0  --> initial： 20-->10
    private static int number = 10;
    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }
}
