package com.andyron.java;

/**
 * -XX:+PrintGCDetails
 * @author andyron
 **/
public class LocalVarGC {
    public void localVarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }
    public void localVarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    public void localVarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }
    public void localVarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    public void localVarGC5() {
        localVarGC1();
        System.gc();
    }


    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();
//        localVarGC.localVarGC1();
//        localVarGC.localVarGC2();
//        localVarGC.localVarGC3();  // buffer不会被回收，局部变量表的第二个槽位还是被占用
//        localVarGC.localVarGC4(); // buffer被回收，局部变量表的第二个槽位被value复用
        localVarGC.localVarGC5(); //
    }
}
