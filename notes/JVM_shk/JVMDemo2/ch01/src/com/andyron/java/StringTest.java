package com.andyron.java;

/**
 * 透过字节码看代码执行细节举例2
 * @author andyron
 **/
public class StringTest {
    public static void main(String[] args) {
        String str = new String("hello") + new String("world");
        String str1 = "helloworld";
        System.out.println(str == str1);  // false
        String str2 = new String("helloworld");
        System.out.println(str == str2); // false
    }
}
