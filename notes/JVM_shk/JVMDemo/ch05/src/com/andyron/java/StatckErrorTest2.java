package com.andyron.java;

/**
 * 演示栈中的异常：StackOverflowError
 * @author andyron
 *
 * 默认情况下：count 19600
 * 设置栈的大小：-Xss640k ，count缩小为为3221
 **/
public class StatckErrorTest2 {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
