package com.andyron.bcdlj.c26;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ex2();
    }

    static void ex1() {
        File f = new File(".");
        File[] files = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        // 将files按照文件名排序
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    static void ex2() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world.");
            }
        });
    }

    static void ex3() {
        File f = new File(".");
        File[] files = f.listFiles((dir, name) -> name.endsWith(".txt"));
        Arrays.sort(files, (f1, f2) -> f1.getName().compareTo(f2.getName()));
    }

    static void ex4() {
        FileFilter filter = path -> path.getName().endsWith(".txt");
        FilenameFilter filenameFilter= ((dir, name) -> name.endsWith(".txt"));

    }
}
