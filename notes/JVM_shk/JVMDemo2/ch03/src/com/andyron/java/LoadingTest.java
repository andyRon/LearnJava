package com.andyron.java;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 过程一：加载阶段
 * 通过Class类，获得饿了.java.lang.String类的所有方法信息，并打印方法访问标识符、描述符
 * @author andyron
 **/
public class LoadingTest {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("java.lang.String");
            // 获取当前运行时类声明的所有方法
            Method[] ms = clazz.getDeclaredMethods();
            for (Method m : ms) {
                // 获取方法的修饰符
                String mod = Modifier.toString(m.getModifiers());
                System.out.print(mod + " ");
                String returnType = m.getReturnType().getSimpleName();
                System.out.print(returnType + " ");
                System.out.print(m.getName() + "(");
                Class<?>[] ps = m.getParameterTypes();
                if (ps.length == 0) {
                    System.out.print(")");
                }
                for (int i = 0; i < ps.length; i++) {
                    char end = (i == ps.length - 1) ? ')' : ',';
                    System.out.print(ps[i].getSimpleName() + end);
                }
                System.out.println();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
