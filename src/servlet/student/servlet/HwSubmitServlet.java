package servlet.student.servlet;

import bean.Grade;
import servlet.student.dao.ClassDao;
import servlet.student.dao.HwSubmitDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HwSubmitServlet extends HttpServlet {
    String stuid;
    String class_id;
    String hwid;
    String text;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        Grade grade = new Grade();
        class_id = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        hwid = request.getParameter("hwid");
        grade.setStuid(request.getParameter("stuid"));
        grade.setHwid(request.getParameter("hwid"));
        grade.setText(request.getParameter("text"));
        HwSubmitDao hwSubmitDao = new HwSubmitDao();
        try {
            hwSubmitDao.hwSubmit(grade);
            request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,class_id));
            String redirect_url ="/CourseManagement_war_exploded/hwcontent?" +
                    "classid="+class_id+"&stuid="+stuid+"&hwid="+hwid;
            response.sendRedirect(redirect_url);

//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("SHomeworkContent.jsp");//转发
//            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
