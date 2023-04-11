package com.andyron.java1;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 局部变量表测试
 * @author andyron
 **/
public class LocalVariablesTest {
    private int count = 0;

    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }

    public static void testStatic() {
        LocalVariablesTest test = new LocalVariablesTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
        // 因为this不存在与静态方法的局部变量表中
//        System.out.println(this.count);
    }

    public LocalVariablesTest() {
        this.count = 1;
    }


    // *********  关于slot的使用理解 ************
    public void test1() {
        Date date = new Date();
        String name1 = "andyron.com";
        String info = test2(date, name1);
        System.out.println(date + name1);
    }

    public String test2(Date dateP, String name2) {
        dateP = null;
        name2 = "rongming";
        double weight = 130.5; // 占据两个slot
        char gender = '男';
        return dateP + name2;
    }

    public void test3() {
        count++;
    }
    public void test4() {
        int a = 0;
        {
            int b = 0;
            b = a + 1;
        }
        // 变量c使用之前已经销毁的变量b占据的slot的位置
        int c = a + 1;
    }
    /*
    java中变量的分类

    按照数据类型分： 1️⃣基本数据类型 2️⃣引用类型数据
    按照在类中声明的位置分：
        1️⃣ 成员变量（在使用前，都经历过**==默认初始化赋值==**）
            类变量（静态变量）：linking的prepare阶段：给类变量默认赋值  -->  inital阶段：给类变量显示赋值及静态代码块赋值
            实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
        2️⃣ 局部变量：在使用前，必须进行显示赋值！否则编译不通过。
     */

    public void test5Temp() {
        int num;
//        System.out.println(num); // 错误：变量num未进行初始化
    }
}
