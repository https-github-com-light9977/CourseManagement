package servlet.teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupServlet extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        String classid = request.getParameter("classid");
        request.setAttribute("classid",classid);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("TGroup.jsp");//转发
        dispatcher.forward(request, response);
    }
}