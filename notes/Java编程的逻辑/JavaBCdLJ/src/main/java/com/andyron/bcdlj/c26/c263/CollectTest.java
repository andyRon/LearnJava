package com.andyron.bcdlj.c26.c263;



import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andyron
 **/
public class CollectTest {
    private List<Student> students = Arrays.asList(new Student[]{
            new Student("zhangsan", 87d),
            new Student("lisi", 89d),
            new Student("wangwu", 92d),
            new Student("andy", 89d),
            new Student("joke", 95d),
            new Student("doc", 81d),
    });


    @Test
    void t() {
        List<Student> above90List = students.stream().filter(t->t.getScore()>85)
                .collect(Collectors.toList());
        System.out.println(above90List);
    }

}
