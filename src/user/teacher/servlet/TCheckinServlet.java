package user.teacher.servlet;

import main.bean.CheckIn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TCheckinServlet extends HttpServlet {
    String class_id;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
        System.out.println(class_id);
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //查询已发布通知列表
            String sql = "select CheckIn_id,CheckIn_deadline from checkin where Class_id='" + class_id + "'";
            ResultSet checkinRes = statement.executeQuery(sql);
            // 处理checkinRes数据，生成列表并储存
            ArrayList<CheckIn> checkInArrayList = new ArrayList<>();
            CheckIn checkIn ;
            while(checkinRes.next()){
                System.out.println("处理结果集");
                checkIn=new CheckIn();
                checkIn.setCheckinid(checkinRes.getString("CheckIn_id"));
                checkIn.setDeadline(checkinRes.getString("CheckIn_deadline"));
                checkInArrayList.add(checkIn);
            }
            request.setAttribute("checkInArrayList", checkInArrayList);
            checkinRes.close();

            //班级信息
            String s = "select Class_id,Class_name,Course_time,Location from course where Class_id='" + class_id + "'";   //返回(班级id,班级名称,上课时间,地点)查询语句
            ResultSet classRes = statement.executeQuery(s);
            //解析classRes
            if (classRes.next()) {
                System.out.println("test");
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
                            request.getRequestDispatcher("TCheckIn.jsp");//转发
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

