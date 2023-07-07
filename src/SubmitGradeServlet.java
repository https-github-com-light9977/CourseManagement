import bean.TCourse;
import bean.Teacher;
import unsolved.TClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmitGradeServlet extends HttpServlet {
    String grade;
    String hw_id;
    String stu_id;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        grade = request.getParameter("grade");
        hw_id = request.getParameter("hwid");
        stu_id = request.getParameter("stuid");
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            String sql = "insert into grade set Grade = '"+grade+"' where Student_id = '"
                    + stu_id + "' and Homework_id = '"+hw_id+"'";
            statement.executeUpdate(sql);

            //转发
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("TClass.jsp");//转发
            dispatcher.forward(request, response);
            con.close();
            statement.close();
        }catch (Exception e){}
            }

        }



