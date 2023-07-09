package servlet.student.dao;

import servlet.student.bean.CourseSel;
import servlet.student.bean.Homework;
import servlet.student.bean.Student;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HwContentDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<String> findHwContent(String hwid,String classid) throws SQLException {
        ArrayList<String> hwContent = new ArrayList<>();
        Homework homework = new Homework();
        conn = db.getConnection();
        String sql = "select Homework_id,Request,ReleaseTime,Homework_Deadline from homework where Class_id=? and Homework_id =?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, classid);
            pstm.setString(2,hwid);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println("test");
            while(rs.next()) {
                homework =  new Homework();
                homework.setHwid(rs.getString(1));
                homework.setHw_requirement(rs.getString(2));
                homework.setReleaseTime(rs.getString(3));
                homework.setDeadline(rs.getString(4));
                hwContent.add(homework.getHwid());
                hwContent.add(homework.getReleaseTime());
                hwContent.add(homework.getDeadline());
                hwContent.add(homework.getHw_requirement());
            }
            return hwContent;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }

        return null;


    }


}