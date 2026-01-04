package com.book.util;

import java.sql.*;

public class JDBCUtil {
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/book_manage?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "12321";

    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // 添加重载方法，方便不同情况使用
    public static void release(Connection connection, Statement statement){
        release(connection, statement, null);
    }

    public static void release(Connection connection){
        release(connection, null, null);
    }
}