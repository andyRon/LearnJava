package com.andyron.java1;

/**
 * 《深入理解Java虚拟机》中的案例：
 * staticObj、instanceObj、localObj三个变量本身存放在哪里？它们的对象放在哪里？
 * staticObj随着Test的类型信息存放在方法区，instanceObj随着Test的对象实例存放在Java堆，localObj则是存放在foo()方法栈帧的局部变量表中。
 * 只要是对象实例必然会在Java堆中分配。
 * @author andyron
 **/
public class StaticObjTest {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }
    private static class ObjectHolder {
    }
    public static void main(String[] args) {
        Test test = new StaticObjTest.Test();
        test.foo();
    }
}
