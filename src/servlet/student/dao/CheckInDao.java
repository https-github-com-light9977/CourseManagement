package servlet.student.dao;

import bean.CheckIn;
import bean.Notice;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckInDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<CheckIn> findCheckIn(String classid) throws SQLException {
        ArrayList<CheckIn> checkInArrayList = new ArrayList<>();
        CheckIn checkIn ;
        conn = db.getConnection();
        String sql =  "select CheckIn_id,CheckIn_deadline from checkin where Class_id=?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            System.out.println(classid);
            pstm.setString(1, classid);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println("test");
            while(rs.next()) {
                checkIn =  new CheckIn();
                checkIn.setCheckinid(rs.getString(1));
                checkIn.setDeadline(rs.getString(2));
                checkInArrayList.add(checkIn);
            }
            return checkInArrayList;

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
