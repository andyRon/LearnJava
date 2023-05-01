package com.andyron.bcdlj.c26.c262;

import com.andyron.bcdlj.c26.c261.Student;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author andyron
 **/
public class StreamTest {
    @Test
    void t1() {
        List<Student> students = Arrays.asList(new Student[]{
                new Student("zhangsan", 87d),
                new Student("lisi", 89d),
                new Student("wangwu", 92d),
                new Student("andy", 89d)

        });
        List<Student> aboveList = students.stream().filter(t -> t.getScore() > 87).collect(Collectors.toList());
        aboveList.stream().forEach(e -> System.out.println(e));

        List<String> nameList = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(nameList);

        List<String> aboveName = students.stream().filter(t -> t.getScore() > 87).map(Student::getName).collect(Collectors.toList());
        System.out.println(aboveName);
    }

    @Test
    void t2() {
        List<String> list = Arrays.asList(new String[]{"abc", "def", "hello", "Abc"});
        List<String> retList = list.stream().filter(s -> s.length() <= 3).map(String::toLowerCase).distinct().collect(Collectors.toList());
        System.out.println(retList);


    }
    private List<Student> students = Arrays.asList(new Student[]{
            new Student("zhangsan", 87d),
            new Student("lisi", 89d),
            new Student("wangwu", 92d),
            new Student("andy", 89d),
            new Student("joke", 95d),
            new Student("doc", 81d),
    });
    @Test
    void t3() {
        List<Student> list = null;
            list = students.stream().filter(t -> t.getScore() > 90)
                .sorted(Comparator.comparing(Student::getScore).reversed().thenComparing(Student::getName))
                .collect(Collectors.toList());
//        list.stream().forEach(s -> System.out.println(s));

        // 将学生列表按照分数排序，返回第3名到第5名
        list = students.stream().sorted(Comparator.comparing(Student::getScore).reversed())
                .skip(2).limit(3).collect(Collectors.toList());
        list.stream().forEach(s -> System.out.println(s));
    }

    @Test
    void t4() {
        List<String> above90Name = students.stream().filter(t -> t.getScore() > 90)
                .peek(System.out::println).map(Student::getName).collect(Collectors.toList());
    }

    @Test
    void t5() {
        double sum = students.stream().mapToDouble(Student::getScore).sum();
        System.out.println(sum);
    }

    @Test
    void t6() {
        List<String> lines = Arrays.asList(new String[]{"hello abc", "老李 编程", "my name is andy"});
        List<String> words = lines.stream().flatMap(line -> Arrays.stream(line.split("\\s+"))).collect(Collectors.toList());
        System.out.println(words);
    }

    @Test
    void t7() {
        // 返回分数最高的学生
        Student student = students.stream().max(Comparator.comparing(Student::getScore)).get();
        System.out.println(student);
    }

    @Test
    void t8() {
        // 判断是不是所有学生都及格了（不小于60分）
        boolean allPass = students.stream().allMatch(t -> t.getScore() >= 6);

        // 随便找一个不及格的学生
        Optional<Student> student = students.stream().filter(t -> t.getScore() < 60).findAny();
        if (student.isPresent()) {
            // 处理不及格的学生
        }

        students.stream().filter(t -> t.getScore() > 90).forEach(System.out::println);
    }

    @Test
    void t9() {
        Student[] above90Arr = students.stream().filter(t -> t.getScore() > 90).toArray(Student[]::new);
        System.out.println(above90Arr[0]);
    }

    @Test
    void t10() {
        // 使用reduce函数求分数最高的学生
        Student topStudent = students.stream().reduce((accu, t) -> {
            if (accu.getScore() >= t.getScore()) {
                return accu;
            } else {
                return t;
            }
        }).get();
    }

    @Test
    void t11() {
        // 输出10个随机数
        Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

        // 输出100个递增的奇数
        Stream.iterate(1, t -> t + 2).limit(100).forEach(System.out::println);

    }
}
