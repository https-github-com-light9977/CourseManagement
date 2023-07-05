import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TCourseServlet extends HttpServlet {
    String teacher_id;
//    String teacher_name;

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Connection con =null;
        Statement statement;
        teacher_id = request.getParameter("teacher_id");
//        teacher_name = request.getParameter("teacher_name");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "select Course_name,time from course where Teacher_id= '" +
                    teacher_id +"'"; // 返回(课程,时间)列表语句
            ResultSet courseRes = statement.executeQuery(sql);

            // 处理courseRes数据，生成列表
            // 存入courseBean,发送至courseList.jsp
            TCourse courseBean=null;
            HttpSession session=request.getSession(true);
            try {
                courseBean = (TCourse) session.getAttribute("courseBean");
                if (courseBean == null) {
                    courseBean = new TCourse();  //创建新的数据模型 。
                    session.setAttribute("loginBean", courseBean);
                    courseBean = (TCourse) session.getAttribute("courseBean");
                }
                //在bean中放入resultSet
                courseBean.setCourseRes(courseRes);
                //转发
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("courseList.jsp");//转发
                dispatcher.forward(request, response);
                con.close();
                statement.close();
                courseRes.close();
            }catch (Exception e){}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
