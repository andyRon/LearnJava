package com.andyron.java2;


/**
 * @author andyron
 **/
public class MyClassLoaderTest {
    public static void main(String[] args) {
        MyClassLoader loader = new MyClassLoader(System.getProperty("user.dir") + "/ch04/src/com/andyron/java1/");

        try {
            Class<?> clazz = loader.loadClass("Demo1");
            System.out.println("加载此类的类加载器为： " + clazz.getClassLoader().getClass().getName());

            System.out.println("加载当前Demo1类的类加载器的父类加载器为：" + clazz.getClassLoader().getParent().getClass().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
