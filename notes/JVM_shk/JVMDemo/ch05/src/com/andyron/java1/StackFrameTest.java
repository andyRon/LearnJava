package com.andyron.java1;

/**
 * @author andyron
 * 方法的结束分为两种：1 正常结束，以return为代表；2 方法执行中出现未捕获处理的异常，以抛出异常的方式结束
 **/
public class StackFrameTest {
    public static void main(String[] args) {
        try {
            StackFrameTest stackFrameTest = new StackFrameTest();
            stackFrameTest.method1();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("main执行结束");
        }

    }

    public void method1() {
        System.out.println("method1开始执行....");
        method2();
        System.out.println("method1执行结束....");
        System.out.println(10/0);
//        return; //  可以省略，class文件中会补上return
    }
    public int method2() {
        System.out.println("method2开始执行....");
        int i = 10;
        int m = (int)method3();
        System.out.println("method2即将结束....");
        return i + m;
    }

    public double method3() {
        System.out.println("method3开始执行....");
        double j = 20.0;
        System.out.println("method3即将结束....");
        return j;
    }
}
