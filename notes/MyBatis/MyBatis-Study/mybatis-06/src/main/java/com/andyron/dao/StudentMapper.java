package com.andyron.dao;

import com.andyron.pojo.Student;

import java.util.List;

public interface StudentMapper {

    // 查询所有学生信息，以及对应老师的信息

    List<Student> getStudent();

    List<Student> getStudent2();
}
