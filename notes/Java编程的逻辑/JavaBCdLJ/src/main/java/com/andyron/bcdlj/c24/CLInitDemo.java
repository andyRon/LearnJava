package com.andyron.bcdlj.c24;

public class CLInitDemo {
    public static class Hello {
        static {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        String className = CLInitDemo.class.getName()+ "$Hello";
        try {
            Class<?> cls = cl.loadClass(className);   // 没有输出
//            Class<?> cls = Class.forName(className);    // 输出 hello
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
