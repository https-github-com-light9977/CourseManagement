package user.student.servlet;


import user.student.bean.SCheckIn;
import user.student.dao.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckInServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String classid,stuid;
        classid = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        CheckInDao checkInDao= new CheckInDao();
        try {
            ArrayList<SCheckIn> checkInArrayList = checkInDao.findCheckIn(classid,stuid);
            request.setAttribute("checkInArrayList",checkInArrayList);
            request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,classid));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("SCheckIn.jsp");//转发
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
