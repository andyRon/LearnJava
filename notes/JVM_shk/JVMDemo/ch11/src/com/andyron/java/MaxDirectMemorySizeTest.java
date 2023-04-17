package com.andyron.java;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -Xmx20m -XX:MaxDirectMemorySize=10m
 * @author andyron
 **/
public class MaxDirectMemorySizeTest {
    public static final long _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        // ByteBuffer.allocateDirect() 中使用Unsafe，这里直接通过反射使用Unsafe
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
