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

public class GroupServlet extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        String classid = request.getParameter("classid");
        ArrayList<Group> groupArrayList = null;
        try {
            String newhwid = new GroupDao().getNewHwid(classid);
            System.out.println(newhwid);
            groupArrayList = new GroupDao().findGroups(classid,newhwid);
            System.out.println(groupArrayList);
        if (groupArrayList.size()>0){
                request.setAttribute("groups",groupArrayList);
            }else {
                request.setAttribute("backnews","暂未分组");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String newhwid = null;
        try {
            newhwid = new GroupDao().getNewHwid(classid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(newhwid);
        request.setAttribute("newhwid",newhwid);
        request.setAttribute("classid",classid);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("TGroup.jsp");//转发
        dispatcher.forward(request, response);
    }
}