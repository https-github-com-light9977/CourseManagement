package servlet.student.dao;

import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SCheckInDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();

    public boolean checkIn(String stuid, String checkin_id) throws SQLException {
        conn = db.getConnection();
        String sql = " select * from checkinsituation where CheckIn_id=?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, checkin_id);
            //更新结果集
            rs = pstm.executeQuery();
            if(rs.next()){return false;}
            else{
                rs.close();
                String insertsql ="insert into checkinsituation values (?,?)";
                pstm = conn.prepareStatement(insertsql);
                //赋值占位符
                pstm.setString(1, stuid);
                pstm.setString(2, checkin_id);
                //更新结果集
                pstm.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }
        return true;
    }
}
