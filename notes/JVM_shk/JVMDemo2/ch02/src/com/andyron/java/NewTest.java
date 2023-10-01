package com.andyron.java;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.File;

/**
 * 指令4：对象、数组的创建与访问指令
 * @author andyron
 **/
public class NewTest {
    // 1 创建指令
    public void newInstance() {
        Object obj = new Object();
        File file = new File("atguigu.avi");
    }
    public void newArray() {
        int[] intArray = new int[10];
        Object[] objArray = new Object[10];
        int[][] mintArray = new int[10][10];

        String[][] strArray = new String[10][]; //  anewarray，此时第二个维度没有赋值，就不初始化，还是一维指令
        String[][] strArray2 = new String[10][5];
    }

    // 2 字段访问指令
    public void sayHello() {
        System.out.println("hello");
    }
    public void setOrderId() {
        Order order = new Order();
        order.id = 1001;
        System.out.println(order.id);

        Order.name = "ORDER";
        System.out.println(Order.name);
    }

    // 3 数组操作指令
    public void setArray() {
        int[] intArray = new int[10];
        intArray[3] = 20;
        System.out.println(intArray[1]);

        boolean[] arr = new boolean[10];
        arr[1] = true;
    }
    public void arrLength() {
        double[] arr = new double[10];
        System.out.println(arr.length);
    }

    // 4 类型检查指令
    public String checkCast(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else {
            return null;
        }
    }
}

class Order {
    int id;
    static String name;
}