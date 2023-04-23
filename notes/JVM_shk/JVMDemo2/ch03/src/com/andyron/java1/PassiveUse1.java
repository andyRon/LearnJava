package com.andyron.java1;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 关于类的被动使用，以不会进行类的初始化操作，也就是不会调用`<clinit>()`
 *
 * 1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
 *    当通过子类引明父类的静态变量，不会导致子类初始化。
 *    > 说明：没有初始化的类，不意味着没有加载！
 * 2. 通过数组定义类引用，不会触发此类的初始化。具体new类是才会进行初始化。
 * 3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就己经被显式赋值了。
 * 4. 调用ClassLoader类的loadClass(）方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 * @author andyron
 **/
public class PassiveUse1 {
    @Test
    public void test1() {
        System.out.println(Child.num);
    }
    
    @Test
    public void test2() {
        Parent[] parents = new Parent[10];  // 定义时没有初始化
        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());

        parents[0] = new Parent();  // 初始化
        parents[1] = new Parent();  // 初始化只会进行一次
    }

    @Test
    public void test3() {
        try {
            ClassLoader.getSystemClassLoader().loadClass("com.andyron.java1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Parent {
    static {
        System.out.println("Parent的初始化过程");
    }
    public static int num = 1;
}
class Child extends Parent {
    static {
        System.out.println("Child的初始化过程");
    }
}

class Person {
    static {
        System.out.println("Person的初始化过程");
    }

    public static final int num = 1;
    public static final int num2 = new Random().nextInt(10);
}