package com.andyron.java;

/**
 * 验证Java没有采用引用计数算法
 *
 * -XX:+PrintGCDetails
 * @author andyron
 **/
public class RefCountGC {
    // 这个成员属性唯一的作用就是占用一点内存
    private byte[] bigSize = new byte[5 * 1024 * 1024];

    Object reference = null;

    public static void main(String[] args) {
        RefCountGC obj1 = new RefCountGC();
        RefCountGC obj2 = new RefCountGC();

        obj1.reference = obj2;
        obj2.reference = obj1;

        obj1 = null;
        obj2 = null;
        // 显示的执行垃圾回收行为
        // 这里发生GC，obj1和obj2能否回收？
        // 如果采用引用计数算法，因为循环引用，应该是能回收，但结果比对，进行了回收，因此证明Java没有采用引用计数算法
//        System.gc();


    }
}
