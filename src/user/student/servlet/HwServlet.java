package user.student.servlet;


import user.student.bean.Homework;
import user.student.dao.ClassDao;
import user.student.dao.HwDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HwServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String classid,stuid;
        classid = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        HwDao hwDao= new HwDao();
        try {
            ArrayList<Homework> homeworkArrayList = hwDao.findHomework(classid);
            request.setAttribute("homeworkArrayList",homeworkArrayList);
            request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,classid));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("SHomework.jsp");//转发
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
