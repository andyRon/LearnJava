package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Andy Ron
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password) {
        String sqlStr = " Select count(*) From t_user "
                + " Where user_name=? and password=? ";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{ userName, password}, Integer.class);
    }

    private final static String MATCH_COUNT_SQL = " Select count(*) From t_user Where user_name=? ";

    private final static String UPDATE_LOGIN_INFO_SQL = " Update t_user Set last_visit=?, last_ip=?, credits=? Where user_id=? ";

    public User findUserByUserName(final String userName) {
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{userName},
                // 匿名类方式实现的回调函数
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                }
        );
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{ user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
