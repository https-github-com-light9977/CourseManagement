package user.teacher.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReleaseHwServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        String classid = request.getParameter("classid");
        System.out.println(classid);
        request.setAttribute("classid",classid);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("ReleaseHw.jsp");//转发
        dispatcher.forward(request, response);
    }
}
