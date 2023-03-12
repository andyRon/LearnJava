package com.andyron.bcdlj.c07.c76;

import java.util.Random;

public class Password {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(randomPassword());
            System.out.println(randomPassword2());
            System.out.println(randomPassword3());
            System.out.println("--------");
        }
    }

    /**
     * 生成随机密码：6位数字
     * @return
     */
    public static String randomPassword() {
        char[] chars = new char[6];
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            chars[i] = (char) ('0' + rnd.nextInt(10));
        }
        return new String(chars);
    }



    /**
     * 生成随机密码：简单8位，大写字母、小写字母、数字和特殊符号组成
     */
    public static String randomPassword2() {
        char[] chars = new char[8];
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            chars[i] = nextChar(rnd);
        }
        return new String(chars);
    }
    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-_";

    private static char nextChar(Random rnd) {
        switch (rnd.nextInt(4)) {
            case 0:
                return (char) ('a' + rnd.nextInt(26));
            case 1:
                return (char) ('A' + rnd.nextInt(26));
            case 2:
                return (char) ('0' + rnd.nextInt(10));
            default:
                return (char) SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
        }
    }

    /**
     * 生成随机密码：复杂8位，至少要含一个大写字母、一个小写字母、一个特殊符号、一个数字
     * nextIndex随机生成一个未赋值的位置，程序先随机生成4个不同类型的字符，放到随机位置上，然后给未赋值的其他位置随机生成字符。
     */
    public static String randomPassword3() {
        char[] chars = new char[8];
        Random rnd = new Random();
        chars[nextIndex(chars, rnd)] = nextSpecialChar(rnd);
        chars[nextIndex(chars, rnd)] = nextUpperLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextLowerLetter(rnd);
        chars[nextIndex(chars, rnd)] = nextNumberLetter(rnd);
        for (int i = 0; i < 8; i++) {
            if (chars[i] == 0) {
                chars[i] = nextChar(rnd);
            }
        }
        return new String(chars);
    }

    private static char nextNumberLetter(Random rnd) {
        return (char) ('0' + rnd.nextInt(10));
    }

    private static char nextLowerLetter(Random rnd) {
        return (char) ('A' + rnd.nextInt(26));
    }

    private static char nextUpperLetter(Random rnd) {
        return (char) ('A' + rnd.nextInt(26));

    }

    private static char nextSpecialChar(Random rnd) {
        return (char) SPECIAL_CHARS.charAt(rnd.nextInt(SPECIAL_CHARS.length()));
    }

    private static int nextIndex(char[] chars, Random rnd) {
        int index = rnd.nextInt(chars.length);
        while (chars[index] != 0) {
            index = rnd.nextInt(chars.length);
        }
        return index;
    }

}
