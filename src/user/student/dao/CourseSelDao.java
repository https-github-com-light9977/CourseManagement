package user.student.dao;

import user.student.bean.CourseSel;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseSelDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<CourseSel> findCourse(String stuid) throws SQLException {
        ArrayList<CourseSel> courseSels = new ArrayList<>();
        CourseSel courseSel ;
        conn = db.getConnection();
        String sql = "select Student_id,Course_name,course.Class_id,Course_time,Location" +
                " from course,courseselection" +
                " where course.Class_id=courseselection.Class_id and Student_id= ? ";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, stuid);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println("test");
            while(rs.next()) {
                courseSel = new CourseSel();
                courseSel.setCourse(rs.getString(2));
                courseSel.setClass_id(rs.getString(3));
                courseSel.setTime(rs.getString(4));
                courseSel.setLocation(rs.getString(5));
                courseSels.add(courseSel);
            }
            return courseSels;

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
