package servlet.student.dao;

import servlet.student.bean.CourseSel;
import servlet.student.bean.Student;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<String> findClassInfo(String stuid,String classid) throws SQLException {
        ArrayList<String> classinfo = new ArrayList<>();
        CourseSel courseSel = new CourseSel();
        conn = db.getConnection();
        String sql = "select Student_id,Class_name,course.Class_id,Course_time,Location" +
                " from course,courseselection" +
                " where course.Class_id=courseselection.Class_id and Student_id= ? and course.Class_id=?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, stuid);
            pstm.setString(2,classid);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println("test");
            while(rs.next()) {
                courseSel.setCourse(rs.getString(2));
                courseSel.setClass_id(rs.getString(3));
                courseSel.setTime(rs.getString(4));
                courseSel.setLocation(rs.getString(5));
                classinfo.add(courseSel.getClass_id());
                classinfo.add(courseSel.getCourse());
                classinfo.add(courseSel.getTime());
                classinfo.add(courseSel.getLocation());
            }
            return classinfo;

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
