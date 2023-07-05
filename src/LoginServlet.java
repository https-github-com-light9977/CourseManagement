import bean.Login;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        String userid = request.getParameter("userid").trim(),
                password = request.getParameter("password").trim();
        HttpSession session = request.getSession(true);
        Login loginBean;
        try {
            loginBean = (Login) session.getAttribute("loginBean");

            if (loginBean == null) {
                loginBean = new Login();  //创建新的数据模型 。
                session.setAttribute("loginBean", loginBean);
                loginBean = (Login) session.getAttribute("loginBean");
            }
            session.setAttribute("loginBean",loginBean);
            loginBean.setLogid(userid);
            loginBean.setBackNews("test");
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("login.jsp");//转发
            dispatcher.forward(request,response);
        } catch (Exception e) {
        }
    }
}