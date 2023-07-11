package user.teacher.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class SubmitGradeServlet extends HttpServlet {
    String grade;
    String hw_id;
    String stu_id;
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        grade = request.getParameter("grade");
        hw_id = request.getParameter("hwid");
        stu_id = request.getParameter("stuid");
        class_id = request.getParameter("classid");
        System.out.println(grade);
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            System.out.println(hw_id);
            System.out.println(stu_id);
            System.out.println(grade);
            String sql = "update grade set Grade = '"+grade+"' where Student_id = '"
                    + stu_id + "' and Homework_id = '"+hw_id+"'";
            statement.executeUpdate(sql);
            System.out.println(grade);
            con.close();
            statement.close();
            //转发
            System.out.println("test1");
//            request.setAttribute("grade",grade);
            System.out.println("test2");

//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("THwDetails.jsp");
//            dispatcher.forward(request, response);
            String redirect_url ="/CourseManagement_war_exploded/hw_submit_list?" +
                    "classid="+class_id+"&hwid="+ hw_id;
            response.sendRedirect(redirect_url);
            System.out.println("转发");
        }catch (Exception e){}
            }

        }



