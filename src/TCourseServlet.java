import bean.Teacher;
import bean.TCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TCourseServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id.equals("1")) {
            request.setCharacterEncoding("utf-8");
            Connection con = null;
            Statement statement;
            //获取请求
            Teacher teacherBean = null;
            HttpSession session = request.getSession(true);
            teacherBean = (Teacher) session.getAttribute("userBean");
            if (teacherBean == null) {
                teacherBean = new Teacher();  //创建新的数据模型 。
                session.setAttribute("userBean", teacherBean);
                teacherBean = (Teacher) session.getAttribute("userBean");
            }

            String teacher_id = teacherBean.getLogid();

            try {

                //连接数据库
//            conn = JdbcUtil.getConnection();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
                    String user = "course_management2023";
                    String db_password = "210470727czyCZY";
                    List<TCourse> courselist= new ArrayList<>();
                    TCourse course ;
                    con = DriverManager.getConnection(url, user, db_password);
                    statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);


                    String sql = "select Course_name,Class_id,Course_time from course where Teacher_id= '" +
                            teacher_id + "'"; // 返回(课程,时间)列表语句
                    ResultSet courseRes = statement.executeQuery(sql);
                    // 处理courseRes数据，生成列表

                    while(courseRes.next()){

                        course=new TCourse();
                        course.setCourseName(courseRes.getString("Course_name"));
                        course.setClassid(courseRes.getString("Class_id"));
                        course.setCourseTime(courseRes.getString("Course_time"));
                        courselist.add(course);
                    }

                    request.setAttribute("courselist",courselist);
                    //在bean中放入resultSet
//                    teacherBean.setCourseRes(courseRes);
                    //转发
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("TeacherCourse.jsp");//转发
                    dispatcher.forward(request, response);
                    con.close();
                    statement.close();
                }
                catch (Exception e) {


                }

            } catch (Exception e) {
                e.printStackTrace();



            }
        }
    }
//    public List<TCourse> handleResult( ResultSet rs) throws SQLException {
//        List<TCourse> courselist= new ArrayList<>();
//        TCourse course ;
//        System.out.println("test");
//        System.out.println(rs.next());
//        while(rs.next()){
//            System.out.println("处理结果集");
//            course=new TCourse();
//            course.setCourseName(rs.getString("Course_name"));
//            course.setClassid(rs.getString("Class_id"));
//            course.setCourseTime(rs.getString("Course_time"));
//            courselist.add(course);
//        }
//        return courselist;
//    }
}
