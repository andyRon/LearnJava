package com.andyron.java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * -XX:+PrintCommandLineFlags
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
