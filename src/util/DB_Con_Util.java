package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB_Con_Util {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";//驱动文件
    private static String URL = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";//数据库本地访问地址
    private static String USER = "course_management2023";//数据库账号
    private static String PASS = "210470727czyCZY";//数据库密码
    Connection connection = null;//声明数据库连接对象，并初始化
    public Connection getConnection() {
        try {
            //加载驱动
            Class.forName(DRIVER);
            //获取数据库连接
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}

