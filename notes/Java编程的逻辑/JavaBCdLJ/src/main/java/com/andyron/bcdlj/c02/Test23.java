package com.andyron.bcdlj.c02;

import java.io.UnsupportedEncodingException;

/**
 * @author andyron
 **/
public class Test23 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Integer i = 39532;
        String str = "0x9A6C";
        System.out.println(Integer.toHexString(i));
        System.out.println(Integer.toBinaryString(i));


        String str2 = "ÀÏÂí";
        String newStr = new String(str2.getBytes("windows-1252"), "GB18030");
        System.out.println(newStr);

        recover(str2);
    }

    // 恢复乱码
    public static void recover(String str) throws UnsupportedEncodingException {
        String[] charsets = new String[]{"windows-1252", "GB18030", "Big5", "UTF-8"};
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                if (i != j) {
                    String s = new String(str.getBytes(charsets[i]), charsets[j]);
                    System.out.println("-----原来编码（A）假设是：" + charsets[j] + ", 被错误解读为了（B）" + charsets[i]);
                    System.out.println(s);
                    System.out.println();
                }
            }
        }
    }

    // 恢复的尝试需要进行很多次，上面例子尝试了常见编码GB18030、Windows 1252、Big5、UTF-8共12种组合。这4种编码是常见编码，在大部分实际应用中应该够了。
}
