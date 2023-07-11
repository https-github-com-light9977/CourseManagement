package user.student.dao;

import user.student.bean.SCheckIn;
import util.DB_Con_Util;

import java.sql.*;
import java.util.ArrayList;

public class CheckInDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<SCheckIn> findCheckIn(String classid,String stuid) throws SQLException {
        ArrayList<SCheckIn> checkInArrayList = new ArrayList<>();
        SCheckIn sCheckIn ;
        String ddl ;
        String checked = "未签到";
        conn = db.getConnection();
        String sql =  "select CheckIn_id from checkin where Class_id=?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            System.out.println(classid);
            pstm.setString(1, classid);
            //查询所有签到id
            rs = pstm.executeQuery();
            ArrayList<String> checks = new ArrayList<>();
            while (rs.next()) {
                checks.add(rs.getString(1));
            }
            rs.close();

            //在每一次签到中查询学生id，查询到将checked赋值为已签到
            for (Integer i = 0; i < checks.size(); i++) {
                checked = "未签到";
                String checkin_id = checks.get(i);
                String checked_sql = "select * from checkinsituation where CheckIn_id='" + checkin_id + "' " +
                        "and Student_id = '" + stuid + "'";
                Statement statement = conn.createStatement();
                ResultSet checkedRes = statement.executeQuery(checked_sql);
                if (checkedRes.next()) {
                    checked = "已签到";
                }
                checkedRes.close();

                //查询签到ddl
                String ddl_sql = "select CheckIn_deadline from checkin where CheckIn_id='" + checkin_id + "' ";
                Statement statement1 = conn.createStatement();
                ResultSet ddlRes = statement1.executeQuery(ddl_sql);
                ddlRes.next();
                ddl = ddlRes.getString(1);

                sCheckIn = new SCheckIn();
                sCheckIn.setCheckin_id(checkin_id);
                sCheckIn.setCheckin_deadline(ddl);
                sCheckIn.setChecked(checked);
                checkInArrayList.add(sCheckIn);
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
