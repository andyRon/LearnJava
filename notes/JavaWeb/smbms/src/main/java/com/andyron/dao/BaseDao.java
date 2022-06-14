package com.andyron.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库公共类
 */
public class BaseDao {
    private static String username;
    private static String password;
    private static String url;
    private static String driver;

    static {
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        username = properties.getProperty("username");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driver = properties.getProperty("driver");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询
     */
    public static ResultSet execute(Connection connection,PreparedStatement pstm,ResultSet rs,
                                    String sql,Object[] params) throws Exception {
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            // 占位符从1开始
            pstm.setObject(i + 1, params[i]);
        }
        rs = pstm.executeQuery();
        return rs;
    }

    /**
     * 更新操作
     */
    public static int execute(Connection connection,PreparedStatement pstm,
                              String sql,Object[] params) throws Exception {
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i + 1, params[i]);
        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

    /**
     * 释放资源
     */
    public static boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet rs) {
        boolean flag = true;
        if(rs != null){
            try {
                rs.close();
                rs = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(pstm != null){
            try {
                pstm.close();
                pstm = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;//GC回收
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}
