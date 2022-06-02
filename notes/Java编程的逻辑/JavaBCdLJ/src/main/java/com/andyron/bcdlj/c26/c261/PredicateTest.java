package com.andyron.bcdlj.c26.c261;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student[]{
            new Student("zhangsan", 87d),
            new Student("lisi", 89d),
            new Student("wangwu", 92d)
        });
        // 过滤90分以上
        students = filter(students, t -> t.getScore() > 90);
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public static <E> List<E> filter(List<E> list, Predicate<E> pred) {
        List<E> retList = new ArrayList<>();
        for (E e : list) {
            if (pred.test(e)) {
                retList.add(e);
            }
        }
        return retList;
    }
}
