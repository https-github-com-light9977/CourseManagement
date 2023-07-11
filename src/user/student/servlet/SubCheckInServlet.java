package user.student.servlet;

import user.student.dao.SubCheckInDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SubCheckInServlet extends HttpServlet {
    String checkin_id;
    String stuid;
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        checkin_id = request.getParameter("checkin_id");
        class_id = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        SubCheckInDao subCheckInDao = new SubCheckInDao();
        try {
            subCheckInDao.checkIn(stuid,checkin_id);
//                request.setAttribute("backnews","已签到");
            System.out.println("转发");
            String redirect_url ="/CourseManagement_war_exploded/scheckin?" +
                        "classid="+class_id+"&stuid="+stuid;
            response.sendRedirect(redirect_url);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
