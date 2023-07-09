package servlet.student.servlet;


import servlet.student.bean.CourseSel;
import servlet.student.dao.ClassDao;
import servlet.student.dao.CourseSelDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String stuid,classid;
        stuid = request.getParameter("stuid");
        classid = request.getParameter("classid");
        try {
            ArrayList<String> classInfo = new ClassDao().findClassInfo(stuid,classid);
            request.setAttribute("classinfo",classInfo);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("SClass.jsp");//转发
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
