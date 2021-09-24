package com.andyron.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Andy Ron
 */
public class MyTest9 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class c1 = Class.forName("com.andyron.reflection.User");

        User user = (User)c1.newInstance(); // 本质是调用无参构造器，如果没有无参构造就会报错
        System.out.println(user);

        // 通过获取构造器创建对象
        Constructor constructor = c1.getDeclaredConstructor(int.class, String.class, int.class);
        User user2 = (User)constructor.newInstance(1, "andyorn", 58);
        System.out.println(user2);

        // 通过反射调用普通方法
        User user3 = (User)c1.newInstance();
        Method setName = c1.getDeclaredMethod("setName", String.class);
        setName.invoke(user3, "小明");  // 激活方法
        System.out.println(user3.getName());

        // 通过反射操作属性
        User user4 = (User)c1.newInstance();
        Field name = c1.getDeclaredField("name");
        // 不能直接操作private属性，要关闭自动安全监测
        name.setAccessible(true);
        name.set(user4, "小戎");
        System.out.println(user4.getName());


    }
}
