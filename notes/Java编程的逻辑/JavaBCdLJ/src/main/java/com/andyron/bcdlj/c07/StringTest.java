package com.andyron.bcdlj.c07;

import java.nio.charset.Charset;

/**
 * @author andyron
 **/
public class StringTest {
    public static void main(String[] args) {
        String str = new String("我aAbc");
        System.out.println(str.codePointAt(0));
        System.out.println(str.codePointBefore(2));
        System.out.println(str.codePointCount(0, 2));

        System.out.println(Charset.defaultCharset());
        System.out.println(Charset.forName("GB18030"));

        System.out.println(str.getBytes());
    }
}
