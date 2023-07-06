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

public class TClassServlet extends HttpServlet {
    String teacher_id;
    String class_id;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;

        //获取请求
//        TCourse courseBean = null;
//        HttpSession session = request.getSession(true);
//        courseBean = (TCourse) session.getAttribute("courseBean");
//        if (courseBean == null) {
//            courseBean = new TCourse();  //创建新的数据模型 。
//            session.setAttribute("courseBean", courseBean);
//            courseBean = (TCourse) session.getAttribute("courseBean");
//        }
//        teacher_id = new Teacher().getLogid();
        class_id = (String) request.getAttribute("classid");
        System.out.println(class_id);
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            String sql = "select Class_id,Class_name,Course_time,Location from course where Class_id='"+ class_id+"'";   //返回(班级id,班级名称,上课时间,地点)查询语句
            ResultSet classRes = statement.executeQuery(sql);
            if( classRes.next()){
                String classname = classRes.getString(2);
                String classtime = classRes.getString(3);
                String location = classRes.getString(4);
                TClass classBean=null;
                try {
                    //在bean中放入resultSet
                    List<String> classlist = new ArrayList<>();
                    classBean.setClassid(class_id);
                    classBean.setClassname(classname);
                    classBean.setClasstime(classtime);
                    classBean.setLocation(location);
                    classlist.add(class_id);
                    classlist.add(classname);
                    classlist.add(classtime);
                    classlist.add(location);
                    request.setAttribute("classlist",classlist);
                    //转发
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("TClass.jsp");//转发
                    dispatcher.forward(request, response);
                    con.close();
                    statement.close();
                    classRes.close();
                }catch (Exception e){}
            }

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



    }
}

