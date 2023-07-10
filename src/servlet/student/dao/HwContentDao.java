package servlet.student.dao;

import servlet.student.bean.CourseSel;
import servlet.student.bean.Homework;
import servlet.student.bean.Student;
import util.DB_Con_Util;

import java.sql.*;
import java.util.ArrayList;

public class HwContentDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    String submited="未完成"
            ,grade = "未批改";
    public ArrayList<String> findHwContent(String hwid,String classid,String stuid) throws SQLException {
        ArrayList<String> hwContent = new ArrayList<>();
        Homework homework = new Homework();
        conn = db.getConnection();
        String sql = "select Homework_id,Request,Grouped from homework where Class_id=? and Homework_id =?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //赋值占位符
            pstm.setString(1, classid);
            pstm.setString(2, hwid);
            //更新结果集
            rs = pstm.executeQuery();

            if(rs.next()) {
                homework.setHwid(rs.getString(1));
                homework.setHw_requirement(rs.getString(2));
                homework.setGrouped(rs.getString(3));

                hwContent.add(homework.getHwid());
                hwContent.add(homework.getHw_requirement());

            }
            rs.close();
            pstm.close();
            conn.close();

            Connection connection = new DB_Con_Util().getConnection();

            String submit_sql = "select Text,Grade from grade where Homework_id = '"+hwid+"' and Student_id='"+stuid+"'";
            Statement statement = connection.createStatement();
            ResultSet submitRes = statement.executeQuery(submit_sql);
            if(submitRes.next()){
                System.out.println("hwcontent");
                    submited = "已提交";
                    System.out.println("hwcontent");
                if(submitRes.getString(2) != null){
                    grade = submitRes.getString(2);
                    System.out.println("hwcontent");
                }
            }
            System.out.println("hwcontent");
            submitRes.close();
            statement.close();
            connection.close();

            homework.setSubmited(submited);
            homework.setGrade(grade);
            hwContent.add(homework.getSubmited());
            hwContent.add(homework.getGrade());
            hwContent.add(homework.getGrouped());
            System.out.println(hwContent.size());
            return hwContent;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
        }

        return null;


    }


}
