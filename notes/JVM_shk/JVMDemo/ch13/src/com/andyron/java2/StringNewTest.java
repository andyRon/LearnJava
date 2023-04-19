package com.andyron.java2;

/**
 * 题目：new String("ab")会创建几个对象？ 看字节码文件
 * 两个，一个对象是new关键字在堆空间创建的
 *      另一个对象是：字符串常量池中的对象。字节码指令：ldc
 *
 * 拓展：new String("a") + new String("b")呢？
 *   对象1：new StringBuilder()
 *   对象2：new String("a")
 *   对象3：常量池中的"a"
 *   对象4：new String("b")
 *   对象5：常量池中的"d"
 *   深入剖析：StringBuilder的toString()：
 *      对象6：new String("ab")
 *      强调一下，toString()的调用，在字符串常量池中，没有生成"ab"
 *
 * @author andyron
 **/
public class StringNewTest {
    public static void main(String[] args) {
//        String str = new String("ab");

        String str = new String("a") + new String("b");
    }
}
