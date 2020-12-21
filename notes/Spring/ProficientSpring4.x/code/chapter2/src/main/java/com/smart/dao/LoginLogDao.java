package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

/**
 * @author Andy Ron
 */
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    // 保存登录日志SQL
    private final static String INSERT_LOGIN_LOG_SQL = " Insert Into t_login_log(user_id,ip,login_datetime Values(?,?,?) ";

    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate() };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
