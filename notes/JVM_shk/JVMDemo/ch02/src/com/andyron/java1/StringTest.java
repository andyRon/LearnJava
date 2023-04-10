package com.andyron.java1;

/**
 * @author andyron
 **/
public class StringTest {
    public static void main(String[] args) {
        // 由于双亲委派机制，java包下优先有启动类加载器加载
        java.lang.String str = new java.lang.String();
        System.out.println("hello, world!");

        /*
        默认你系统类加载器询问上一级扩展类加载器：你需要加载StringTest；
        扩展类加载器询问上一级启动类加载器：你需要加载StringTest；
        启动类加载器说不需要 -> 扩展类加载器也说不需要 -> 系统类加载器说那还是我来吧
         */
        StringTest test = new StringTest();
        System.out.println(test.getClass().getClassLoader());
    }
}
