package user.student.servlet;

import main.bean.Grade;
import user.student.dao.ClassDao;
import user.student.dao.HwContentDao;
import user.student.dao.HwSubmitDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HwSubmitServlet extends HttpServlet {
    String stuid;
    String classid;
    String hwid;
    String text;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        System.out.println("hwsubmit");
        Grade grade = new Grade();
        classid = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        hwid = request.getParameter("hwid");
        grade.setStuid(request.getParameter("stuid"));
        grade.setHwid(request.getParameter("hwid"));
        grade.setText(request.getParameter("text"));
        HwSubmitDao hwSubmitDao = new HwSubmitDao();
        System.out.println("hwsubmit");
        try {
            System.out.println("hwsubmit");
            if(!new HwContentDao().isGroupHw(hwid,classid,stuid)) {
                hwSubmitDao.hwSubmit(grade);
            }else {
                System.out.println("ghwsubmit");
                hwSubmitDao.groupHwSubmit(grade);
            }
            request.setAttribute("classinfo", new ClassDao().findClassInfo(stuid, classid));
            String redirect_url = "/CourseManagement_war_exploded/hwcontent?" +
                    "classid=" + classid + "&stuid=" + stuid + "&hwid=" + hwid;
            response.sendRedirect(redirect_url);

//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("SHomeworkContent.jsp");//转发
//            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
