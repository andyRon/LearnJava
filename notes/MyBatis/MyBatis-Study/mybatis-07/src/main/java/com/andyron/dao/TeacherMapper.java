package com.andyron.dao;

import com.andyron.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {

    List<Teacher> getTeacher();

    // 获取指定老师信息，及其所有学生的信息
    Teacher getTeacherPlus(@Param("tid") int id);
    Teacher getTeacherPlus2(@Param("tid") int id);
}
