package com.andyron.pojo;

import lombok.Data;

/**
 * @author Andy Ron
 */
@Data
public class Student {
    private int id;
    private String name;
    // 学生需要关联一个老师
    private Teacher teacher;
}
