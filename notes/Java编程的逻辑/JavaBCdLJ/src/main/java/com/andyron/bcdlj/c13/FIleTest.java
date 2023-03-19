package com.andyron.bcdlj.c13;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FIleTest {
    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("./hello.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getCanonicalFile().getAbsolutePath());
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
        System.out.println(File.pathSeparator);
        File file1 = new File("./dd.txt");
        System.out.println(file1.exists());
        System.out.println(file1.isDirectory());
        System.out.println(file.length());
        System.out.println(file.lastModified());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.parse(dateFormat.format(file.lastModified())));
        System.out.println("====");
        File dir = new File("./");
        for (String s : dir.list()) {
            System.out.println(s);
        }
        System.out.println("--------");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        for (File f : files) {
            System.out.println(f.getName());
        }

    }
}
