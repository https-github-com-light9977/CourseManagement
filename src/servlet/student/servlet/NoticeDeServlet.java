package servlet.student.servlet;


import bean.Notice;
import servlet.student.bean.CourseSel;
import servlet.student.bean.Homework;
import servlet.student.dao.ClassDao;
import servlet.student.dao.CourseSelDao;
import servlet.student.dao.HwDao;
import servlet.student.dao.NoticeDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class NoticeDeServlet  extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response){
        String classid,stuid,no_id;
        classid = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        no_id = request.getParameter("no_id");
        NoticeDao noticeDao= new NoticeDao();
        try {
            ArrayList<String> noticeArrayList = noticeDao.findNoticeDetail(classid,no_id);
            request.setAttribute("noDetail",noticeArrayList);
            request.setAttribute("classinfo",new ClassDao().findClassInfo(stuid,classid));
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("SNoticeDetail.jsp");//转发
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
