package servlet.student.dao;

import bean.Grade;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HwSubmitDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    String stuid;
    String hwid;
    String text;
    public void hwSubmit(Grade grade) throws SQLException {
        conn = db.getConnection();
        stuid = grade.getStuid();
        hwid = grade.getHwid();
        text = grade.getText();
        String sql = " select * from grade where Homework_id=? and Student_id = ? ";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, hwid);
            pstm.setString(2, stuid);
            //更新结果集
            rs = pstm.executeQuery();
            if(!rs.next()){
                rs.close();
                String insertsql ="insert into grade(Student_id,Homework_id,Text) values (?,?,?)";
                pstm = conn.prepareStatement(insertsql);
                //赋值占位符
                pstm.setString(1, stuid);
                pstm.setString(2, hwid);
                pstm.setString(3, text);
                //更新结果集
                pstm.executeUpdate();
                System.out.println("插入成功");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }
    }
}
