package servlet.teacher.dao;

import bean.Group;
import servlet.student.bean.CourseSel;
import servlet.student.bean.Homework;
import servlet.student.bean.Student;
import util.DB_Con_Util;

import java.sql.*;
import java.util.ArrayList;

public class GroupDao {
    String newhwid;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();



    public ArrayList<Group> findGroups(String classid,String newhwid) throws SQLException {
        ArrayList<Group> groupArrayList = new ArrayList<>();
        Group group ;
        conn = db.getConnection();
            String sql =  "select distinct Homework_id from `group` where Class_id=?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //赋值占位符
            pstm.setString(1, classid);
            //更新结果集
            rs = pstm.executeQuery();

            if(rs.last()&&rs.getString(1).equals(newhwid)) { //找到上一次分组作业id
                String lasthwid = rs.getString(1);
                System.out.println(lasthwid);
                rs.close();
                String sql_group = "select Class_id,Group_id,Student_id,Homework_id from `group` where Homework_id = ?";
                PreparedStatement pstm2 = conn.prepareStatement(sql_group,ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                pstm2.setString(1, newhwid);
                System.out.println("show test");
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {
                    group = new Group();
                    group.setClassid(rs2.getString(1));
                    group.setGroupid(rs2.getString(2));
                    group.setStuid(rs2.getString(3));
                    String stuid = rs2.getString(3);
                    Statement stmt = conn.createStatement();
                    ResultSet rs3 = stmt.executeQuery("select Student_name from student where Student_id = '"+stuid+"'");
                    rs3.next();
                    String stuname =rs3.getString(1);
                    group.setStuname(stuname);
                    rs3.close();
                    group.setHwid(rs2.getString(4));
                    groupArrayList.add(group);

                }
            }
            return groupArrayList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            conn.close();
            pstm.close();
        }

        return null;


    }



        public String getNewHwid(String classid) throws SQLException {
            ArrayList<Group> groupArrayList = new ArrayList<>();
            Group group ;
            conn = db.getConnection();
            String sql =  "select distinct Homework_id from homework where Class_id=?";
            try {
                //	预编译sql
                pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                //赋值占位符
                System.out.println(classid);
                pstm.setString(1, classid);
                //更新结果集
                rs = pstm.executeQuery();

                if(rs.last()) {
                    String lasthwid = rs.getString(1);
                    rs.close();
                    //确认本次作业id
                    String lastnum = lasthwid.substring(8);
                    if(Integer.parseInt(lastnum)<9){
                        newhwid = classid + "0" + (Integer.parseInt(lastnum)+1);
                    }else {
                        newhwid = classid + (Integer.parseInt(lastnum)+1);
                    }

                }else{
                    newhwid = classid + "01";
                }
                System.out.println(newhwid);
                return newhwid;

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //释放资源
                conn.close();
                pstm.close();
            }
            return null;
        }



    public void insertGroups(String classid,ArrayList<Group> groups,String hwid) throws SQLException {
        conn = db.getConnection();
        try {
            //	预编译sql
            String sql =  "select distinct Homework_id from `group` where Class_id=?";
            pstm = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //赋值占位符
            System.out.println(classid);
            pstm.setString(1, classid);
            //更新结果集
            rs = pstm.executeQuery();
            rs.next();
            System.out.println(hwid);
            System.out.println(rs.getString(1));
            if (hwid.equals(rs.getString(1))) {//当重复进行多次分组时,更新group表。
                rs.close();
                System.out.println("更新");
                String sql_group = "delete from  `group` where Homework_id = ?";
                    pstm = conn.prepareStatement(sql_group, ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                    pstm.setString(1, hwid);
                    pstm.executeUpdate();
            }
                for (int i = 0; i < groups.size(); i++) {
                    String sql_group = "insert into `group` values(?,?,?,?)";
                    pstm = conn.prepareStatement(sql_group, ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                    pstm.setString(1, classid);
                    pstm.setString(2, groups.get(i).getGroupid());
                    pstm.setString(3, groups.get(i).getStuid());
                    pstm.setString(4, hwid);
                    pstm.executeUpdate();
                }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            conn.close();
            pstm.close();
        }



    }




}
