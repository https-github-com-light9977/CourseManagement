package user.student.servlet;


import user.student.dao.ClassDao;
import user.student.dao.HwContentDao;
import user.student.dao.SGroupDao;

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
        HwContentDao hwContentDao = new HwContentDao();
        try {
            if(hwContentDao.isGroupHw(hwid,classid,stuid)){
                ArrayList<String> groupMembers =new SGroupDao().findGroup(hwid,stuid);
                ArrayList<String> hwContent = hwContentDao.findHwContent(hwid,classid,stuid);
                request.setAttribute("groupMembers",groupMembers);
                request.setAttribute("hwContent",hwContent);
                request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,classid));
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("SGroupHw.jsp");//转发
                dispatcher.forward(request, response);
            }else {

                ArrayList<String> hwContent = hwContentDao.findHwContent(hwid, classid, stuid);
                request.setAttribute("hwContent", hwContent);
                request.setAttribute("classinfo", new ClassDao().findClassInfo(stuid, classid));
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("SHomeworkContent.jsp");//转发
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
