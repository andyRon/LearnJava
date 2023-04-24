package com.andyron.java;

/**
 * @author andyron
 **/
public class ClassLoaderTest1 {
    public static void main(String[] args) {

        try {
            ClassLoader cl = Class.forName("com.andyron.java.ClassLoaderTest1").getClassLoader();
            System.out.println(cl);  // AppClassLoader@18b4aac2

            // 关于数组类型的类加载器
            String[] arrStr = new String[10];
            System.out.println(arrStr.getClass().getClassLoader()); // null，引导类加载器

            ClassLoaderTest1[] arr1 = new ClassLoaderTest1[10];
            System.out.println(arr1.getClass().getClassLoader()); // $AppClassLoader@18b4aac2 与数组元素的加载器是相同的

            int[] arr2 = new int[10];
            System.out.println(arr2.getClass().getClassLoader()); // null 表示不需要类加载器

            System.out.println(Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
