package com.andyron.java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * -XX:+PrintCommandLineFlags
 * -XX:+UseSerialGC 表明年轻代Serial GC，同时老年代Serial Old GC。
 * -XX:+UseConcMarkSweepGC 使用CMSGC
 * -XX:+UseParNewGC
 *
 * -XX:+UseParallelGC
 *
 * -XX:+UseConcMarkSweepGC
 * @author andyron
 **/
public class GCUseTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();
        while (true) {
            byte[] arr = new byte[100];
            list.add(arr);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
