package com.andyron.bcdlj.c24.c244;

public class MyClassLoaderDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader cl1 = new MyClassLoader();
        String className = "com.andyron.bcdlj.c24.CLInitDemo";
        Class<?> class1 = cl1.loadClass(className);

        MyClassLoader cl2 = new MyClassLoader();
        Class<?> class2 = cl2.loadClass(className);

        System.out.println(class1.hashCode());
        System.out.println(class2.hashCode());
        if (class1 != class2) {
            System.out.println("两个类不同");
        }
    }
}
