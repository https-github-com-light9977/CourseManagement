package user.student.servlet;

import main.bean.Grade;
import user.student.dao.ClassDao;
import user.student.dao.HwContentDao;
import user.student.dao.HwSubmitDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
@MultipartConfig
public class HwSubmitServlet extends HttpServlet {
    String stuid;
    String classid;
    String hwid;
    String text;
    Part part;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        request.getCharacterEncoding();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        System.out.println("hwsubmit");
        Grade grade = new Grade();
        classid = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        hwid = request.getParameter("hwid");
        System.out.println(hwid);
        System.out.println(classid);
        grade.setStuid(request.getParameter("stuid"));
        grade.setHwid(request.getParameter("hwid"));
        text = request.getParameter("text");
        grade.setText(request.getParameter("text"));
        part =request.getPart("file");//获取表单提交的文件
        grade.setPart(part);

        System.out.println(grade.getText());
        HwSubmitDao hwSubmitDao = new HwSubmitDao();
        System.out.println("hwsubmit");
        try {
            System.out.println(hwid);
            if(!new HwContentDao().isGroupHw(hwid)) {
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
