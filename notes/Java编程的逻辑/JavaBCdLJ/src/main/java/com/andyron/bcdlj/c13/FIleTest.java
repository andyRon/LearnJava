package com.andyron.bcdlj.c13;

import com.andyron.bcdlj.c09.ArrayListTest;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FIleTest {
    static File curDir = new File("./");
    public static void main(String[] args) throws Exception {
        t1();
//        t2();
//        System.out.println(sizeOfDirectory(curDir));
//
//        System.out.println(findFile(curDir, "pom.xml"));
    }

    static void t1() throws IOException, ParseException {
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

    }

    static void t2() {
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

    public static long sizeOfDirectory(final File directory) {
        long size = 0;
        if (directory.isFile()) {
            return directory.length();
        } else {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += sizeOfDirectory(file);
                }
            }
        }
        return size;
    }

    public static Collection<File> findFile(final File directory, final String fileName) {
        List<File> files = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().equals(fileName)) {
                files.add(file);
            } else if(file.isDirectory()) {
                files.addAll(findFile(file, fileName));
            }
        }
        return files;
    }

    /**
     * 删除非空目录
     */
    public static void deleteRecursively(final File file) throws IOException {
        if (file.isFile()) {
            if (!file.delete()) {
                throw new IOException("删除文件失败 " + file.getCanonicalPath());
            }
        } else if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteRecursively(f);
            }
            if (!file.delete()) {
                throw new IOException("删除失败 " + file.getCanonicalPath());
            }
        }
    }
}
