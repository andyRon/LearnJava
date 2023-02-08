package com.andyron.test;

import java.sql.*;

/**
 * @author Andy Ron
 */
public class TestJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        test1();
        String str = "adfafdasf";
        System.out.println(Integer.toBinaryString(10));
    }

    static void test1() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "iop654321";

        // 1 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2 链接数据库，connection相当于代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3 向数据库发送SQL的对象Statement或PreparedStatement（安全的，预编译），用它来做crud
        Statement statement = connection.createStatement();

        // 4 编写SQL
        String sql = "select * from users";

        // 5 执行查询SQL，返回一个ResultSet：结果集
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            System.out.println("id=" + rs.getObject("id"));
            System.out.println("name=" + rs.getObject("name"));
            System.out.println("password=" + rs.getObject("password"));
            System.out.println("email=" + rs.getObject("email"));
            System.out.println("birthday=" + rs.getObject("birthday"));
        }

        // 6 关闭链接，释放资源（一点要做）。先开的后关闭
        rs.close();
        statement.close();
        connection.close();
    }

    static void test2() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "iop654321";

        // 1 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2 链接数据库，connection相当于代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3 编写SQL
        String sql = "insert into users(id, name, password, email, birthday) VALUES (?,?,?,?,?)";

        // 4 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,4);  // 给第一个占位符？赋值
        preparedStatement.setString(2, "王麻子");
        preparedStatement.setString(3, "wmz12345");
        preparedStatement.setString(4, "wmz@163.com");
        preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));

        // 5 执行SQL
        int i = preparedStatement.executeUpdate();
        if (i>0) {
            System.out.println("插入成功");
        }
        // 6 关闭链接，释放资源（一点要做）。先开的后关闭
        preparedStatement.close();
        connection.close();
    }

    static void test3() {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "iop654321";

        Connection connection = null;

        try {
            // 1 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2 链接数据库，connection相当于代表数据库
            connection = DriverManager.getConnection(url, username, password);

            // 3 通知数据库开启事务，false表示开启
            connection.setAutoCommit(false);

            String sql1 = "update account set money = money - 100 where name = 'A'";
            connection.prepareStatement(sql1).executeUpdate();

            // 制造错误
            int i = 1/0;

            String sql2 = "update account set money = money + 100 where name = 'B'";
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit(); // 两条SQL都提交成功，就提交事务
            System.out.println("success");

        } catch (Exception e) {
            // 如果出现异常通知数据库回滚事务
            try {
                connection.rollback();
                System.out.println("rollback");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
