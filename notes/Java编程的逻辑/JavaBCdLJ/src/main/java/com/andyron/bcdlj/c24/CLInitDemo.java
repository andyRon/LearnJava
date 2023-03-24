package com.andyron.bcdlj.c24;

/**
    ClassLoader的loadClass不会执行类的初始化代码，而Class的forName方法是执行的。
 */
public class CLInitDemo {
    public static class Hello {
        static {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        String className = CLInitDemo.class.getName()+ "$Hello";
        System.out.println(className);
        try {
//            Class<?> cls = cl.loadClass(className);   // 没有输出
            Class<?> cls = Class.forName(className);    // 输出 hello
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
