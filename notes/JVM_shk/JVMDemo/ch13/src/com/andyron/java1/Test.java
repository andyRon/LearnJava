package com.andyron.java1;

/**
 * @author andyron
 **/
public class Test {
    public static void main(String[] args) {
        String s1 = "andy";
        String s2 = new String("andy");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1 == s2);

    }
}
