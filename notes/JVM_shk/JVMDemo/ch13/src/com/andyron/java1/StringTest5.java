package com.andyron.java1;

import org.junit.jupiter.api.Test;

/**
 * 字符串拼接操作
 * @author andyron
 **/
public class StringTest5 {
    @Test
    public void test1() {
        // 字符串字面量拼接结果还是存在常量池里，而且是编译器优化，
        // 查看class文件发现代码变为`String s1 = "abc";`
        String s1 = "a" + "b" + "c";
        String s2 = "abc";  // "abc"一定是放在字符串常量池中的，将地址赋给s2

        System.out.println(s1 == s2);  // true
        System.out.println(s1.equals(s2)); // true
    }

    @Test
    public void test2() {
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";  // 编译器优化
        // 如果拼接符号的前后出现了变量，则相当月在堆空间中new String()
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4);  // true
        System.out.println(s3 == s5);  // false
        System.out.println(s3 == s6);  // false
        System.out.println(s3 == s7);  // false
        System.out.println(s5 == s6);  // false
        System.out.println(s5 == s7);  // false
        System.out.println(s6 == s7);  // false

        // intern()的作用，是判断字符串常量池中是否存在javaEEhadoop，不存在就建一个并返回地址，存在返回对应地址
        String s8 = s6.intern();
        System.out.println(s3 == s8);  // true
    }

    @Test
    public void test3() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        /*
          s1 + s2的细节：
          1️⃣ StringBuilder s = new StringBuilder();
          2️⃣ s.append("a")
          3️⃣ s.append("b")
          4️⃣ s.toSring()  --> 约等于 new String("ab")

         补充：在jdk5.0之后使用StringBuilder，
         之前使用StringBuffer
         */
        String s4 = s1 + s2;
        System.out.println(s3 == s4); // false
    }

    /*
      如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译器优化，而不是StringBuilder的方式。
     */
    @Test
    public void test4() {
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";

        String s4 = s1 + s2;
        System.out.println(s3 == s4); // true
    }

    // 练习
    @Test
    public void test5() {
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3); // false

        final String s4 = "javaEE";
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5); // true
    }

    /*
    体验执行效率: 使用StringBuilder的append()的方式添加字符串效率远高于使用String的字符串拼接方式。
    ️1️⃣ 前者自始至终创建了一个StrinBuilder对象，后者创建了n个StringBulder和Sring对象
     2️⃣ 后者由于创建了过多对象，还有可能需要GC

    改进的空间：创建StringBuilder时，如果知道大概长度时，可以实现创建这个长度的SB，这样可以省去不断扩容的时间

     */
    @Test
    public void test6() {
        long start = System.currentTimeMillis();
        int highLevel = 100000;
        method1(highLevel);  // 1826
//        method2(highLevel); // 2
//        method2_plus(highLevel); // 1

        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start));
    }


    private void method1(int highLevel) {
        String src = "";
        for (int i = 0; i < highLevel; i++) {
            src = src + "a"; // 每次循环都会创建一个StringBuilder、String
        }
    }

    private void method2(int highLevel) {
        // 只创建一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }

    private void method2_plus(int highLevel) {
        // 只创建一个StringBuilder
        StringBuilder src = new StringBuilder(highLevel);
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }
}
