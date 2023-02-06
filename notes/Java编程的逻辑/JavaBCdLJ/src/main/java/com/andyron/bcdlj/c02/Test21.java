package com.andyron.bcdlj.c02;

/**
 * ClassName: Test21
 * Package: com.andyron.bcdlj.c02
 * Description:
 *
 * @Author: andyron
 * @Create: 2023/2/5 - 12:40
 * @Version: v1.0
 **/
public class Test21 {
    public static void main(String[] args) {

        int a = 25;
        a = 0x7B;
        a = 0b11001;
        System.out.println(Integer.toBinaryString(a)); //二进制
        System.out.println(Integer.toHexString(a));  //十六进制
        System.out.println(Long.toBinaryString(a)); //二进制
        System.out.println(Long.toHexString(a));  //十六进制

        int b = 4;
        int c = -4;
        System.out.println(b >> 1);
        System.out.println(b << 2);
        System.out.println(Integer.toBinaryString(c));
        System.out.println(c >>> 1);
        System.out.println(Integer.toBinaryString(c >>> 1));

        System.out.println(b & 0x1);

        System.out.println(0.1f + 0.1f);
        System.out.println(0.1f * 0.1f);


        System.out.println(Integer.toBinaryString(Float.floatToIntBits(1.2345f)));
        System.out.println(Long.toBinaryString(Double.doubleToLongBits(1.2345f)));
    }
}
