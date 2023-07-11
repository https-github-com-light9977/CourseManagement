package user.teacher.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class THwDetailsServlet extends HttpServlet {
    String student_id;
    String homework_id;
    String class_id;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        student_id = request.getParameter("stuid");
        homework_id = request.getParameter("hwid");
        class_id = request.getParameter("classid");
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            String sql = "select student.Student_id,student.Student_name,Text,Grade,Homework_id,File_name from grade,student where student.Student_id = grade.Student_id and student." +
                    "Student_id='" + student_id + "'"
                    + "and Homework_id = '" + homework_id + "'";
            ResultSet hwDetailsRes = statement.executeQuery(sql);

            //解析ResultSet
            if (hwDetailsRes.next()) {
                System.out.println("test");
                String stuid = hwDetailsRes.getString(1);
                String stuname = hwDetailsRes.getString(2);
                String text = hwDetailsRes.getString(3);
                String grade = hwDetailsRes.getString(4);
                String filename = hwDetailsRes.getString(6);
                if(grade == null) {
                    grade = "未批改";
                }
                System.out.println(text);

                //将结果打包成list传入前端
                ArrayList<String> hwDetail = new ArrayList<>();
                hwDetail.add(stuid);
                hwDetail.add(stuname);
                hwDetail.add(text);
                hwDetail.add(grade);
                hwDetail.add(homework_id);
                hwDetail.add(filename);
                System.out.println(hwDetail.get(5));
                //前端展示时只取前四个数据
                request.setAttribute("hwDetail", hwDetail);
                hwDetailsRes.close();


                //班级信息
                String s = "select Class_id,Class_name,Course_time,Location from course where Class_id='" + class_id + "'";   //返回(班级id,班级名称,上课时间,地点)查询语句
                ResultSet classRes = statement.executeQuery(s);
                //解析classRes
                if (classRes.next()) {
                    String classname = classRes.getString(2);
                    String classtime = classRes.getString(3);
                    String location = classRes.getString(4);
                    try {
                        //将结果打包成list传入前端
                        List<String> classinfo = new ArrayList<>();
                        classinfo.add(class_id);
                        classinfo.add(classname);
                        classinfo.add(classtime);
                        classinfo.add(location);
                        request.setAttribute("classinfo", classinfo);

                        //转发
                        RequestDispatcher dispatcher =
                                request.getRequestDispatcher("THwDetails.jsp");//转发
                        dispatcher.forward(request, response);
                        con.close();
                        statement.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }

                }
            }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

