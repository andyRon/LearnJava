package com.andyron.java2;

/**
 * 如何保证变量s指向的是字符串常量池中的数据？
 * 两种方式：
 * - String s = "andy";  // 字面量定义的方式
 * - 调用intern()
 *      String s = new String("andy").intern();
 *      String s = new StringBuilder("andy").toString().intern();
 *
 * @author andyron
 **/
public class StrigIntern {
    public static void main(String[] args) {
        String s = new String("1");  // 堆中有"1"，字符串常量池也有"1"，并且把堆中"1"对象的地址赋给s
        s.intern();  // 调用次方法之前，字符串常量池中已经存在"1"
        String s2 = "1";  // 字符串常量池中的"1"的地址
        System.out.println(s == s2);  // jdk6:false   jdk7/8:false


        String s3 = new String("1") + new String("1");  //
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);  // jdk6:false   jdk7/8:true
    }
}
