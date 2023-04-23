package com.andyron.java1;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 测试类的主动使用：意味着会调用类的<clinit>()，即执行了类的初始化阶段
 *
 * 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：`Class.forName("com.andyron.java.Test")`
 * 5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 * 6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。
 * 7. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main(）方法的那个类），虛拟机会先初始化这个主类。
 *
 * 针对5，补充说明：
 * 当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口。
 * - 在初始化一个类时，并不会先初始化它所实现的接口
 * - 在初始化一个接口时，并不会先初始化它的父接口
 * 因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，才会导致该接口的初始化。
 * -XX:+TraceClassLoading  查看加载类信息
 * @author andyron
 **/
public class ActiveUse3 {
    static {
        System.out.println("ActiveUse3类的初始化过程");
    }
    @Test
    public void test() {
        try {
            Class clazz = Class.forName("com.andyron.java1.Order");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        System.out.println(Son.num);
    }

    @Test
    public void test3() {
        System.out.println(CompareC.NUM1);
    }

    @Test
    public void test4() {
        System.out.println(Son.num);
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

class Father {
    static {
        System.out.println("Father类的初始化过程");
    }
}
class Son extends Father implements CompareB {
    static {
        System.out.println("Son类的初始化过程");
    }
    public static int num = 1;
}
interface CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareB的初始化");
        }
    };

    public default void method1() {
        System.out.println("你好！");
    }
}

interface CompareC extends CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareC的初始化");
        }
    };

    public static final int NUM1 = new Random().nextInt();
}