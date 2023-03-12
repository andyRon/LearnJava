package com.andyron.bcdlj.c07;

/**
 * @author andyron
 **/
public class CharacterTest {
    public static void main(String[] args) {
        // 0x10FFFF = 1,114,111
        System.out.println(Character.isValidCodePoint(1114112));
        // 0xFFFF = 65535
        System.out.println(Character.isBmpCodePoint(65536));

        System.out.println(Character.isSupplementaryCodePoint(65536));

        System.out.println(Character.isHighSurrogate('\uD800'));
        System.out.println(Character.isHighSurrogate('\uDC00'));
        System.out.println(Character.isLowSurrogate('\uDC00'));
        System.out.println(Character.isSurrogate('\uE000'));

        System.out.println(Character.charCount(1114112));

        System.out.println("------------");
//        System.out.println(Character.toCodePoint('\u0001', '\uF000'));
//        System.out.println(Character.toChars(65536)[1]);


        System.out.println("--------------");
        System.out.println(Character.getType('A'));
        System.out.println(Character.getType('1'));
        System.out.println(Character.getType('l'));
        System.out.println(Character.getType('{'));
        System.out.println(Character.getType(1114110));

        System.out.println(Character.isDefined(1114110));

        char ch = '9'; //中文全角数字
        System.out.println((int)ch+", "+Character.isDigit(ch));

        System.out.println(Character.isAlphabetic('Ⅳ'));


        System.out.println("-----------");

        System.out.println(Character.digit('E', 16));
        System.out.println(Character.digit('G', 18));
        System.out.println(Character.forDigit(13, 16));
    }
}
