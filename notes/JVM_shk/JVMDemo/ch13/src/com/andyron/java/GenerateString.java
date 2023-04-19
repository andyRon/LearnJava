package com.andyron.java;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 产生10万个长度不超过10的字符串，包含a-z，A-Z
 * @author andyron
 **/
public class GenerateString {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("word.txt");
        for (int i = 0; i < 100000; i++) {
            int length = (int) (Math.random() * (10 - 1 + 1) + 1);
            fw.write(getString(length) + "\n");
        }
        fw.close();
    }

    private static String getString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            int num = (int) (Math.random() * (90 - 65 + 1) + 65) + (int) (Math.random() * 2) * 32;
            str += (char) num;
        }
        return str;
    }
}
