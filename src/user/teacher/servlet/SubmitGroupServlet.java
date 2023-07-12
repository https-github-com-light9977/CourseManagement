package user.teacher.servlet;

import main.bean.Group;
import user.teacher.dao.GroupDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubmitGroupServlet extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        String newhwid = request.getParameter("newhwid");
        String classid = request.getParameter("classid");
        ArrayList<Group> groups = (ArrayList<Group>) request.getAttribute("groups");
        GroupDao groupDao = new GroupDao();
//        try {
//            groupDao.insertGroups(classid,groups,newhwid);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        request.setAttribute("backnews","已分组");
        request.setAttribute("classid",classid);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("ReleaseHw.jsp");//转发
        dispatcher.forward(request, response);

    }
}