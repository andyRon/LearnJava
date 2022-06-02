package com.andyron.bcdlj.c26.c261;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    public static <E> void foreach(List<E> list, Consumer<E> consumer) {
        for (E e : list) {
            consumer.accept(e);
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(new Student[]{
                new Student("zhangsan", 87d),
                new Student("lisi", 89d),
                new Student("wangwu", 92d)
        });
        foreach(students, t -> t.setName(t.getName().toUpperCase()));
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
