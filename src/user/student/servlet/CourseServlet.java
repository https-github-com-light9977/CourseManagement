package user.student.servlet;


import user.student.bean.CourseSel;
import user.student.dao.CourseSelDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String stuid;
        stuid = request.getParameter("stuid");
        CourseSelDao courseSelDao= new CourseSelDao();
        try {
            ArrayList<CourseSel> courseSels = courseSelDao.findCourse(stuid);
            request.setAttribute("courselist",courseSels);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("StudentCourse.jsp");//转发
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
