package com.andyron.bcdlj.c24;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        t2();
    }

    static void t1() {
        ClassLoader cl = Demo.class.getClassLoader();
        while (cl != null) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }
        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    static void t2() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        try {
            Class<?> cls = cl.loadClass("java.util.ArrayList");
            ClassLoader actualLoader = cls.getClassLoader();
            System.out.println(actualLoader);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Demo {

}
