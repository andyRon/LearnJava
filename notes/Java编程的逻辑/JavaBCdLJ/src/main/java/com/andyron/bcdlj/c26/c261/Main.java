package com.andyron.bcdlj.c26.c261;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
//        ex1();
//        ex2();
        ex1_1();
    }
    // 内部类写法
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
    // lambda表达式写法
    static void ex1_1() {
        File f = new File(".");
        File[] files = f.listFiles((dir, name) -> name.endsWith(".txt"));
        Arrays.sort(files, (f1, f2) -> f1.getName().compareTo(f2.getName()));
//        Arrays.sort(files, Comparator.comparing(File::getName));
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
    static void ex2_1() {
        Executors.newFixedThreadPool(100).submit(() -> System.out.println("Hello world!"));
    }



    static void ex4() {
        FileFilter filter = path -> path.getName().endsWith(".txt");
        FilenameFilter filenameFilter= ((dir, name) -> name.endsWith(".txt"));
        Comparator<File> comparator = (f1, f2) -> f1.getName().compareTo(f2.getName());
        Runnable task = () -> System.out.println("Hello World.");

        Predicate<File> predicate = f -> f.getName().endsWith(".txt");
    }

    /**
     * 方法引用的不同情况
     */
    static void ex5() {
        Supplier<String> s1 = Student::getCollegeName;
        Supplier<String> s2 = () -> Student.getCollegeName();

        Function<Student, String> f1 = Student::getName;
        Function<Student, String> f2 = (Student t) -> t.getName();

        BiConsumer<Student, String> c1 = Student::setName;
        BiConsumer<Student, String> c2 = (t, name) -> t.setName(name);


        Student t = new Student("andy", 99.9d);
        Supplier<String> s3 = t::getName;
        Supplier<String> s4 = () -> t.getName();

        Consumer<String> c3 = t::setName;
        Consumer<String> c4 = (name) -> t.setName(name);

        BiFunction<String, Double, Student> s5 = Student::new;
        BiFunction<String, Double, Student> s6 = (name, score) -> new Student(name, score);


    }

}


