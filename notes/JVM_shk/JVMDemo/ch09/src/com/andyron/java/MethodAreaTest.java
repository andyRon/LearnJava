package com.andyron.java;

/**
 * 静态变量被类的所有实例共享，即使没有类实例时你也可以访问它。
 * @author andyron
 **/
public class MethodAreaTest {
    public static void main(String[] args) {
      Order order = null;
      order.hello();
        System.out.println(order.count);
    }
}
class Order {
    public static int count = 1;
    public static final int number = 2;
    public static void hello() {
        System.out.println("hello!");
    }
}
