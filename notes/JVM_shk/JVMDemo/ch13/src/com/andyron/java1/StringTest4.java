package com.andyron.java1;

/**
 * @author andyron
 **/
public class StringTest4 {
    public static void main(String[] args) {
        System.out.println();  // 1161
        System.out.println("1"); // 1162  增加一个换行
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");  // 1171

        System.out.println("1");  // 1172
        System.out.println("2");  // 1172
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("10");  // 1172  字符串常量池里同样的字符串只能保持一个，所以数量不再变化
    }
}
