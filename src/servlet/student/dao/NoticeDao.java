package servlet.student.dao;

import bean.Notice;
import servlet.student.bean.CourseSel;
import servlet.student.bean.Homework;
import servlet.student.bean.Student;
import util.DB_Con_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoticeDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    public ArrayList<Notice> findNotice(String classid) throws SQLException {
        ArrayList<Notice> noticeArrayList = new ArrayList<>();
        Notice notice ;
        conn = db.getConnection();
        String sql =  " select Notice_id,Content,NoticeTime from notice where Class_id=?";
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
                notice =  new Notice();
                notice.setNoticeid(rs.getString(1));
                String content = rs.getString(2);
                if (content.length()>15){content=content.substring(15)+"...";}
                notice.setContent(content);
                notice.setTime(rs.getString(3));
                noticeArrayList.add(notice);
            }
            return noticeArrayList;

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

    public ArrayList<String> findNoticeDetail(String classid,String no_id) throws SQLException {
        ArrayList<String> noticeArrayList = new ArrayList<>();
        Notice notice ;
        conn = db.getConnection();
        String sql =  " select Notice_id,Content,NoticeTime from notice where Class_id=? and Notice_id =?";
        try {
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            System.out.println(classid);
            pstm.setString(1, classid);
            pstm.setString(2, no_id);
            //更新结果集
            rs = pstm.executeQuery();
            System.out.println("test");
            while(rs.next()) {
                notice =  new Notice();
                notice.setNoticeid(rs.getString(1));
                String content = rs.getString(2);
                notice.setContent(content);
                notice.setTime(rs.getString(3));
                noticeArrayList.add(notice.getNoticeid());
                noticeArrayList.add(notice.getTime());
                noticeArrayList.add(notice.getContent());
            }
            return noticeArrayList;

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
