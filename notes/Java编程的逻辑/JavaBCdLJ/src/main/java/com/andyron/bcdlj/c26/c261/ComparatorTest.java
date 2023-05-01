package com.andyron.bcdlj.c26.c261;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author andyron
 **/
public class ComparatorTest {
    @Test
    void t1() {
        File f = new File(".");
        File[] all = f.listFiles();
        Arrays.sort(all, Comparator.comparing(File::getName));
        for (File file : all) {
            System.out.println(file.getName());
        }
    }

    @Test
    void t2() {
        List<Student> students = Arrays.asList(new Student[]{
                new Student("zhangsan", 87d),
                new Student("lisi", 89d),
                new Student("wangwu", 92d),
                new Student("andy", 89d)

        });
        // 将学生列表按照分数倒序排（高分在前），分数一样的按照名字进行排序
        students.sort(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getName));

        for (Student s : students) {
            System.out.println(s);
        }
    }
}
