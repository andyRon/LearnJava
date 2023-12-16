package com.andyron.bcdlj.c24.c242;

public class ClassLoaderDemo {

    public static void main(String[] args) {
//        t1();
        t2();
    }

    static void t1() {
        ClassLoader cl = Demo.class.getClassLoader();
        while (cl != null) {
            System.out.println(cl.getClass().getName());
            cl = cl.getParent();
        }
        System.out.println(String.class.getClassLoader());  // null, Bootstrap ClassLoader不是由Java实现的，没有对应的类
        System.out.println(ClassLoader.getSystemClassLoader());
    }


    static void t2() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        System.out.println(cl);
        try {
            // java.util.ArrayList实际由BootStrap ClassLoader加载，所以返回值就是null。
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
