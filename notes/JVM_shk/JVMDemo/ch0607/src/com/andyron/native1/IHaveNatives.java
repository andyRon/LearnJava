package com.andyron.native1;

public class IHaveNatives {
    public native void methodNative1(int x);
    public native static long methodNative2();
    private native synchronized float methodNative3(Object o);
    native  void methodNative4(int[] ary) throws Exception;
}