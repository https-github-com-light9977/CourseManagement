package servlet.teacher;

import bean.TCourse;
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
import java.util.List;

public class THomeworkServlet extends HttpServlet {
    String class_id;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
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
            String sql = "select Homework_id,Request,Homework_Deadline from homework where Class_id='" + class_id + "'";
            ResultSet hwRes = statement.executeQuery(sql);
            // 处理hwRes数据，生成列表并储存
            ArrayList<THomework> homeworks = new ArrayList<>();
            THomework hw ;
            while(hwRes.next()){

                hw=new THomework();
                hw.setHwid(hwRes.getString("Homework_id"));
                hw.setHw_requirement(hwRes.getString("Request"));
                hw.setDeadline(hwRes.getString("Homework_Deadline"));
                homeworks.add(hw);
            }
            request.setAttribute("homeworks", homeworks);
            hwRes.close();


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
                            request.getRequestDispatcher("THomeWork.jsp");//转发
                    dispatcher.forward(request, response);
                    con.close();
                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

