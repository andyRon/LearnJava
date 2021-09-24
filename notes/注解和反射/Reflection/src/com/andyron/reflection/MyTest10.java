package com.andyron.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Andy Ron
 */
// 性能测试
public class MyTest10 {

    // 普通方式调用
    public static void test1() {
        User user = new User();
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            user.getName();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("普通方式调用10亿次耗时" + (endtime - starttime) + "ms");
    }

    // 反射方式调用
    public static void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        User user = new User();
        Class c = user.getClass();
        Method getName = c.getDeclaredMethod("getName", null);

        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(user, null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("反射方式调用10亿次耗时" + (endtime - starttime) + "ms");
    }

    // 反射方式调用 关闭监测
    public static void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        User user = new User();
        Class c = user.getClass();
        Method getName = c.getDeclaredMethod("getName", null);
        getName.setAccessible(true);

        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(user, null);
        }
        long endtime = System.currentTimeMillis();
        System.out.println("反射方式调关闭监测用10亿次耗时" + (endtime - starttime) + "ms");
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        test1();
        test2();
        test3();
    }
}
