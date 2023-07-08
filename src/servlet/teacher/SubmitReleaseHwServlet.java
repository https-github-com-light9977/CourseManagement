package servlet.teacher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubmitReleaseHwServlet extends HttpServlet {
    String class_id;
    String hwid;
    String hw_requirement;
    String deadline;
    String releaseTime;
    String grouped;
    String groupid;
    String submit;
    public void service(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
        releaseTime = request.getParameter("publishTime");
        deadline = request.getParameter("deadline");
        grouped = request.getParameter("group");
        hw_requirement = request.getParameter("content");

        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023?allowMultiQueries=true";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //查询作业列表是否为空,设置hwid
            String condition1 = "select  count(*) as cnt from homework where Class_id='" + class_id + "'";
            String condition2 = "select Homework_id from homework where Class_id='" + class_id + "'";
            ResultSet hwExist = statement.executeQuery(condition1);
            hwExist.next();
            System.out.println("res test");
            if("0".equals(hwExist.getString("cnt"))){
                hwid = class_id + "1";
                hwExist.close();
            }else{
                ResultSet getLast = statement.executeQuery(condition2);
                getLast.last();
                String last_hwid;
                last_hwid = getLast.getString(1);
                hwid = class_id + ((Integer.parseInt(last_hwid.substring(8)))+1);
                System.out.println(hwid);
                getLast.close();
            }
            System.out.println("insert");
            // 插入数据
            String insertsql = "insert into homework(Homework_id,Class_id,ReleaseTime,Homework_Deadline,Grouped,Request) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertsql);
            preparedStatement.setString(1,hwid);
            preparedStatement.setString(2,class_id);
            preparedStatement.setString(3,releaseTime);
            preparedStatement.setString(4,deadline);
            preparedStatement.setString(5,grouped);
            preparedStatement.setString(6,hw_requirement);
            preparedStatement.executeUpdate();
            System.out.println("insert");

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
//                    RequestDispatcher dispatcher =
//                            request.getRequestDispatcher("THomeWork.jsp");//转发
//                    dispatcher.forward(request, response);
                    String redirect_url ="/CourseManagement_war_exploded/homework?" +
                            "classid="+class_id;
                    response.sendRedirect(redirect_url);
                    System.out.println("转发");

                    con.close();
                    statement.close();

                } catch (SQLException e) {
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

