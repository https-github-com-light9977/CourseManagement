import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class T_classServlet extends HttpServlet {
    String teacher_id;
    String class_id;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        teacher_id = new Login().getLogid();
        class_id = request.getParameter("class_id");
        try {
//          //连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            String sql = "";   //返回(班级id,班级名称,上课时间,地点)查询语句
            ResultSet classRes = statement.executeQuery(sql);
            if( classRes.next()){
                String classname = classRes.getString(2);
                String classtime = classRes.getString(3);
                String location = classRes.getString(4);
                T_Class classBean=null;
                HttpSession session=request.getSession(true);
                try {
                    classBean = (T_Class) session.getAttribute("courseBean");
                    if (classBean == null) {
                        classBean = new T_Class();  //创建新的数据模型 。
                        session.setAttribute("classBean", classBean);
                        classBean = (T_Class) session.getAttribute("classBean");
                    }
                    //在bean中放入resultSet
                    classBean.setClassid(class_id);
                    classBean.setClassname(classname);
                    classBean.setClasstime(classtime);
                    classBean.setLocation(location);
                    //转发
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("class_homework.jsp");//转发
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

