package servlet.student.servlet;


import servlet.student.bean.CourseSel;
import servlet.student.dao.ClassDao;
import servlet.student.dao.CourseSelDao;
import servlet.student.dao.HwContentDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HwContentServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String stuid,classid,hwid;
        stuid = request.getParameter("stuid");
        classid = request.getParameter("classid");
        hwid =request.getParameter("hwid");
        try {
            ArrayList<String> hwContent = new HwContentDao().findHwContent(hwid,classid);
            request.setAttribute("hwContent",hwContent);
            request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,classid));
            System.out.println((new ClassDao().findClassInfo(stuid,classid)).get(0));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("SHomeworkContent.jsp");//转发
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
