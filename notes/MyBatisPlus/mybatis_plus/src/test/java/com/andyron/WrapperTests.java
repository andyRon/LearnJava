package com.andyron;

import com.andyron.mapper.UserMapper;
import com.andyron.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Andy Ron
 */
@SpringBootTest
public class WrapperTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void test1() {
        // 查询name,email不为空，年龄大于等于14的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 14);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "andy");
        System.out.println(userMapper.selectOne(wrapper));
    }

    @Test
    void test3() {
        // 查询年龄在20-30之间的用户数
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        System.out.println(userMapper.selectCount(wrapper));
    }

    // 模糊查询
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 正常like %e% 左 %e  右 e%
        wrapper.notLike("name", "e")  // 名字中不包括e
                .likeRight("email", "t"); // 邮箱以t开头的
        /*
        AND name NOT LIKE '%e%'
        AND email LIKE 't%'
         */
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id在子查询中查出来
        wrapper.inSql("id", "select id from user where id < 4");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
