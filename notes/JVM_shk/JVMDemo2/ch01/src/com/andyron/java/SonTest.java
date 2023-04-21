package com.andyron.java;

/**
 * 透过字节码看代码执行细节举例3
 *
 * 成员变量（非静态的）的赋值过程：
 * 1 默认初始化 2 显示初始化/代码块中初始化 3 构造器中初始化
 * 4 有了对象之后，可以"对象.属性"或"对象.方法"的对成员变量进行赋值
 * @author andyron
 **/
class Father {
    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }
    public void print() {
        System.out.println("Father.x = " + x);
    }
}
class Son extends Father {
    int x = 30;
    public Son() {
        this.print();
        x = 40;
    }
    public void print() {
        System.out.println("Son = " + x);
    }
}
public class SonTest {
    public static void main(String[] args) {
//        Father f = new Father();
        Father f = new Son();
        System.out.println(f.x);
    }
}
