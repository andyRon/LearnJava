package com.andyron.java;

/**
 * jdk9+
 * @author andyron
 **/
public class ClassLoaderTest {
    // FIXME
    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader()); // jdk.internal.loader.ClassLoaders$AppClassLoader@3b192d32
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent()); // jdk.internal.loader.ClassLoaders$PlatformClassLoader@1cd072a9
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent()); // null

        System.out.println(ClassLoader.getSystemClassLoader());
//        System.out.println(ClassLoader.getPlatformClassLoader());

        // 获取类加载器的名称
//        System.out.println(ClassLoaderTest.class.getClassLoader().getName());


    }
}
