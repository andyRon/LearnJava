package com.andyron.java1;

/**
 * @author andyron
 **/
public class StrongRerenceTest {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("hello, world");
        StringBuffer str1 = str;

        str = null;
        System.gc();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(str1);
    }


}
