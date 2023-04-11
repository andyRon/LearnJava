package com.andyron.java1;

/**
 * @author andyron
 **/
public class OperandStackTest {
    public void testAddOperation() {
        // byte, short, char, boolean 都是以int存储的
        byte i = 15;
        int j = 8;
        int k = i + j;

        int m = 800;
    }

    public  int getSum() {
        int m = 10;
        int n = 20;
        int k = 10 + 20;
        return k;
    }

    public void testGetSum() {
        // 获取上一个栈帧返回结果，并保存在操作数栈中
        int i = getSum();
        int j = 10;
    }

    /*
    // TODO
     面试题：常见的i++和++i的区别，放到字节码篇章学习介绍。
     ？？其实这个只要知道i++ 与 ++i操作都是直接在局部变量表中进行，没有操作数栈的出栈入栈操作就大概能理解了

     for循环中，在Java中i++语句是需要一个临时变量取存储返回自增前的值，而++i不需要。
     这样就导致使用i++时系统需要先申请一段内存空间，然后将值塞如进去，最后不用了才去释放。多了这么一系列操作时间。
     */
    public void add() {
        // 第1类问题
        int i1 = 10;
        i1++;

        int i2 = 10;
        ++i2;

        // 第2类问题
        int i3 = 10;
        int i4 = i3++;

        int i5 = 10;
        int i6 = ++i5;

        // 第3类问题
        int i7 = 10;
        i7 = i7++;

        int i8 = 10;
        i8 = ++i8;

        // 第4类问题
        int i9 = 10;
        int i10 = i9++ + ++i9;
    }
}
