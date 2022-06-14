package com.andyron.dao;

import com.andyron.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    /**
     * 增加用户信息
     */
    public int add(Connection connection, User user) throws Exception;

    /**
     * 通过userCode获取User
     */
    public User getLoginUser(Connection connection, String userCode) throws Exception;

    /**
     * 通过条件查询-userList
     */
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception;
    /**
     * 通过条件查询-用户表记录数
     */
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception;

    /**
     * 通过userId删除user
     */
    public int deleteUserById(Connection connection, Integer delId) throws Exception;


    /**
     * 通过userId获取user
     */
    public User getUserById(Connection connection, String id) throws Exception;

    /**
     * 修改用户信息
     */
    public int modify(Connection connection, User user) throws Exception;


    /**
     * 修改当前用户密码
     */
    public int updatePwd(Connection connection, int id, String pwd) throws Exception;
}
