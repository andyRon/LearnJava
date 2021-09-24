package com.andyron.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Andy Ron
 */
public class MyTest8 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.andyron.reflection.User");

        // 获得类的名字
        System.out.println(c1.getName()); // 包名+类名
        System.out.println(c1.getSimpleName()); // 类名

        System.out.println("===================");
        // 获得类的属性
        Field[] fields = c1.getFields(); // 只能获得public属性
        for (Field field : fields) {
            System.out.println(field);
        }
        fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // 获得指定属性
        System.out.println(c1.getDeclaredField("name"));

        System.out.println("===================");

        // 获得方法
        Method[] methods = c1.getMethods();  // 获得本类及其父类的所有public方法
        for (Method method : methods) {
            System.out.println("getMethods:" + method);
        }
        methods = c1.getDeclaredMethods();  // 获得本类的或有方法
        for (Method method : methods) {
            System.out.println("getDeclaredMethods" + method);
        }
        // 获得指定方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        System.out.println("===================");

        // 获得构造器方法
        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }

    }
}
