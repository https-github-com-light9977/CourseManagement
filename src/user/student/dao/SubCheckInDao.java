package user.student.dao;

import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubCheckInDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    String ddl=null ;

    public boolean checkIn(String stuid, String checkin_id) throws SQLException {
        conn = db.getConnection();
        String sql = " select * from checkinsituation where CheckIn_id=?";
        String sql2 = " select CheckIn_deadline from checkin where CheckIn_id=?";
        try {
            pstm = conn.prepareStatement(sql2);
            pstm.setString(1,checkin_id);
            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                ddl = resultSet.getString(1);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date ddl_time = sdf.parse(ddl) ;
            Date cur_time = sdf.parse(sdf.format(System.currentTimeMillis()));

            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, checkin_id);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println(ddl_time);
            System.out.println(cur_time);
            System.out.println(ddl_time.before(cur_time));
        if(!ddl_time.before(cur_time)) {
            if (rs.next()) {
                return false;
            } else {
                rs.close();
                String insertsql = "insert into checkinsituation values (?,?)";
                pstm = conn.prepareStatement(insertsql);
                //赋值占位符
                pstm.setString(1, stuid);
                pstm.setString(2, checkin_id);
                //更新结果集
                pstm.executeUpdate();
                System.out.println("插入成功");
                return true;
            }
        }else {return false;}

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }
        return false;
    }
}
