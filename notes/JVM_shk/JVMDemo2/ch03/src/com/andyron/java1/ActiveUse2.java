package com.andyron.java1;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Random;

/**
 * 测试类的主动使用：意味着会调用类的<clinit>()，即执行了类的初始化阶段
 *
 * 3. 当使用类、接口的静态字段时(final修饰特殊考虑），比如，使用`getstatic`或者`putstatic`指令。（对应访问变量、赋值变量操作）
 * @author andyron
 **/
public class ActiveUse2 {

    @Test
    public void test1() {
//        System.out.println(User.num);
//        System.out.println(User.num1);
        System.out.println(User.num2);
    }

    @Test
    public void test2() {
//        System.out.println(CompareA.NUM1);
        System.out.println(CompareA.NUM2);
    }
}
class User {
    static {
        System.out.println("User类的初始过程");
    }

    public static int num = 1;
    public static final int num1 = 1;
    public static final int num2 = new Random().nextInt(10);

}

interface CompareA {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareA的初始化"); // 接口CompareA如果进行了初始化就会运行
        }
    };

    public static final int NUM1 = 1;
    public static final int NUM2 = new Random().nextInt(10);
}