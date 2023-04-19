package com.andyron.java1;

/**
 * @author andyron
 **/
public class Memory {
    public static void main(String[] args) {
        int i = 1;
        Object obj = new Object();
        Memory mem = new Memory();
        mem.foo(obj);
    }

    private void foo(Object param) {
        String str = param.toString();  // line 7
        System.out.println(str);
    }
}
