package user.student.dao;

import main.bean.Grade;
import util.DB_Con_Util;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
@MultipartConfig
public class HwSubmitDao {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = null;
    //工具包中的数据库连接方法
    DB_Con_Util db = new DB_Con_Util();
    String stuid;
    String hwid;
    String text;
    Part part;
    public void hwSubmit(Grade grade) throws SQLException {
        conn = db.getConnection();
        stuid = grade.getStuid();
        hwid = grade.getHwid();
        text = grade.getText();
        part = grade.getPart();
        InputStream fileInputStream=null;//文件输入流
        String filename = part.getSubmittedFileName();
        String sql = " select * from grade where Homework_id=? and Student_id = ? ";
        try {
            System.out.println(part.getContentType());
            if (part!=null){
                System.out.println(part.getContentType());
                fileInputStream= part.getInputStream(); }

            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, hwid);
            pstm.setString(2, stuid);
            //更新结果集
            rs = pstm.executeQuery();
            if(! rs.next()){
                rs.close();
                String insertsql ="insert into grade(Student_id,Homework_id,Text,Student_file,File_name) values (?,?,?,?,?)";
                pstm = conn.prepareStatement(insertsql);
                //赋值占位符
                pstm.setString(1, stuid);
                pstm.setString(2, hwid);
                pstm.setString(3, text);
                pstm.setBinaryStream(4, fileInputStream);
                pstm.setString(5, filename);

                //更新结果集
                pstm.executeUpdate();
                System.out.println("插入成功");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }
    }

    public void groupHwSubmit(Grade grade) throws SQLException {
        conn = db.getConnection();
        stuid = grade.getStuid();
        hwid = grade.getHwid();
        text = grade.getText();
        part = grade.getPart();
        InputStream fileInputStream=null;//文件输入流
        String filename = part.getSubmittedFileName();
        String sql = " select * from grade where Homework_id=? and Student_id = ? ";
        try {
            System.out.println(part.getContentType());
            if (part!=null){
                System.out.println(part.getContentType());
                fileInputStream= part.getInputStream(); }

            System.out.println("hwsubmit");
            //	预编译sql
            pstm = conn.prepareStatement(sql);
            //赋值占位符
            pstm.setString(1, hwid);
            pstm.setString(2, stuid);
            //更新结果集
            rs = pstm.executeQuery();
            if(! rs.next()){
                rs.close();
                ArrayList<String> groupMember = new SGroupDao().findGroupMemberId(hwid,stuid);
                for (int i =0;i<groupMember.size();i++){
                    String insertsql ="insert into grade(Student_id,Homework_id,Text,Student_file,File_name) values (?,?,?,?,?)";
                    pstm = conn.prepareStatement(insertsql);
                    //赋值占位符
                    pstm.setString(1, stuid);
                    pstm.setString(2, hwid);
                    pstm.setString(3, text);
                    pstm.setBinaryStream(4, fileInputStream);
                    pstm.setString(5, filename);
                    System.out.println(text);
                    //更新结果集
                    pstm.executeUpdate();
                    System.out.println("插入成功");
                }

            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            conn.close();
            pstm.close();
            rs.close();
        }
    }
}
