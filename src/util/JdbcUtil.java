package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    public static String username;
    public static String password;
    public static String driver;
    public static String url;

    static {
        // -1.读取配置文件
        InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");

            // 0.加载驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        // 1.连接数据库
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        // 5.关闭连接
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (stmt!=null){
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {

                try {
                    if (conn!=null){
                        conn.close();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


}
