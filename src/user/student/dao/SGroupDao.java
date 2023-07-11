package user.student.dao;

import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SGroupDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();

    public ArrayList<String> findGroup(String hwid, String stuid) throws SQLException {
        ArrayList<String> groupMember = new ArrayList<>();
        conn = db.getConnection();
        String sql = "select student.student_id,Student_name, Homework_id,group_id " +
                "from student, `group`" +
                "where student.student_id= `group`.student_id and  homework_id=? and group_id=(select group_id from `group` where student_id=? and  homework_id=?)";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //赋值占位符
            pstm.setString(1, hwid);
            pstm.setString(2, stuid);
            pstm.setString(3, hwid);
            //更新结果集
            rs = pstm.executeQuery();
            while (rs.next()) {
                groupMember.add(rs.getString(2));
            }
            rs.close();
            pstm.close();
            conn.close();
            System.out.println(groupMember);

            return groupMember;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
        }

        return null;


    }

    public ArrayList<String> findGroupMemberId(String hwid, String stuid) throws SQLException {
        ArrayList<String> groupMember = new ArrayList<>();
        conn = db.getConnection();
        String sql = "select student.student_id,Student_name, Homework_id,group_id " +
                "from student, `group`" +
                "where student.student_id= `group`.student_id and  homework_id=? and group_id=(select group_id from `group` where student_id=? and  homework_id=?)";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //赋值占位符
            pstm.setString(1, hwid);
            pstm.setString(2, stuid);
            pstm.setString(3, hwid);
            //更新结果集
            rs = pstm.executeQuery();
            while (rs.next()) {
                groupMember.add(rs.getString(1));
            }
            rs.close();
            pstm.close();
            conn.close();
            System.out.println(groupMember);

            return groupMember;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
        }

        return null;


    }
}
