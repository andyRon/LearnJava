package com.andyron.java;

import org.junit.jupiter.api.Test;

/**
 * String基本使用：体现String的不可变性
 *
 * @author andyron
 **/
public class StringTest1 {
    @Test
    public void test1() {
        String s1 = "abc";  // 字面量定义的方式，"abc"存储在字符串常量池中，只能有一个
        String s2 = "abc";

        System.out.println(s1 == s2);  // 判断是地址：true

        s2 = "hello";  // 在字符串常量池再创造"hello"并把地址给s2
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        s2 += "def";
        System.out.println(s2);  // 字符串不可变性，任何修改都是在字符串常量池里重新创造一个
        System.out.println(s1);
    }

    @Test
    public void test3() {
        String s1 = "abc";
        String s2 = s1.replace('a', 'm');
        System.out.println(s1);
        System.out.println(s2);
    }

    @Test
    public void test4() {
        String s1 = "abc";
        String s2 = "中华人民共和国";

        System.out.println(s2);
    }

}
