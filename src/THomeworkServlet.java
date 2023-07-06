import unsolved.TClass;
import bean.THomework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class THomeworkServlet extends HttpServlet {
    String class_id;
    public void doGet(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = new TClass().getClassid();
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //查询作业列表（作业id，作业要求，截止时间）语句
            String sql = "select Homework_id,Request,Homework_Deadline from homework where Class_id='"+class_id+"'";
            ResultSet hwRes = statement.executeQuery(sql);
            // 处理hwRes数据，生成列表并储存
            ArrayList<THomework> homeworks = new ArrayList<>();
            while(hwRes.next()){
                System.out.println("处理结果集");
                THomework hw=new THomework();
                hw.setHwid(hwRes.getString(1));
                hw.setHw_requirement(hwRes.getString(2));
                hw.setDeadline(hwRes.getString(3));
                homeworks.add(hw);
            }
            System.out.println(homeworks);
            request.setAttribute("homeworks",homeworks);
            //转发
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("THomeWork.jsp");//转发
            dispatcher.forward(request, response);
            con.close();
            statement.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}

