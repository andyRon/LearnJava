package com.andyron;

import com.andyron.dao.UserDao;
import com.andyron.pojo.User;
import com.andyron.utils.MybatisUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> list = mapper.getUserList();
        for (User user : list) {
            System.out.println(user.getName());
        }
        sqlSession.close();
    }

    @Test
    public void t() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();


    }
}
