package servlet.student.servlet;

import servlet.student.dao.SCheckInDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SCheckInServlet extends HttpServlet {
    String checkin_id;
    String stuid;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        checkin_id = request.getParameter("checkin_id");
        stuid = request.getParameter("stuid");
        SCheckInDao sCheckInDao = new SCheckInDao();
        try {
            if(sCheckInDao.checkIn(stuid,checkin_id)){
                request.setAttribute("backnews","已签到");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
