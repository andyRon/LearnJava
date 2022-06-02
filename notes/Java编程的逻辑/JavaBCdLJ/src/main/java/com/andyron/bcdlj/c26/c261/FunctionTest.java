package com.andyron.bcdlj.c26.c261;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionTest {
    public static <T, R> List<R> map(List<T>list, Function<T, R> mapper) {
        List<R> retList = new ArrayList<>(list.size());
        for (T e : list) {
            retList.add(mapper.apply(e));
        }
        return retList;
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student[]{
                new Student("zhangsan", 87d),
                new Student("lisi", 89d),
                new Student("wangwu", 92d)
        });
        List<String> names = map(students, t -> t.getName());
        System.out.println(names);

        students = map(students, t -> new Student(t.getName().toUpperCase(), t.getScore()));
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
