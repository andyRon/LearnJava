package com.andyron.dao;

import com.andyron.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 查询所有用户
     */
    List<User> getUserList();

    /**
     * 根据id查询
     */
    User getUserById(int id);

    /**
     * 插入一个用户
     */
    int addUser(User user);


    /**
     * 跟新一个用户
     */
    int updateUser(User user);

    /**
     * 删除一个用户
     */
    int deleteUser(int id);

}
