package com.andyron.dao;

import com.andyron.pojo.User;
import com.andyron.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author Andy Ron
 */
public class UserDaoTest {

    @Test
    public void test() {
        // 第一步：获得SQLSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // 执行SQL
        // 方式一：getMapper
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.getUserList();

        // 方式二（不推荐了）
//        List<User> userList = sqlSession.selectList("com.andyron.dao.UserDao.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭SQLSession
        sqlSession.close();
    }
}
