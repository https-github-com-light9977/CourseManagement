import com.mysql.cj.jdbc.JdbcConnection;
import util.JdbcUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class ResetPasswordServlet extends HttpServlet {
        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;
        String backnews = "test";
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
        }

        public void service(HttpServletRequest request,
                            HttpServletResponse response)
                throws ServletException, IOException {
            String id = request.getParameter("id");
            String old_password = request.getParameter("old-password");
            String new_password = request.getParameter("new-password");
            String confirm_password = request.getParameter("confirm-password");

            //连接数据库
            conn = JdbcUtil.getConnection();

            //处理数据表
            try {
                String sql = "select Teacher_password from teacher where Teacher_id = ? ";
                stmt=conn.prepareStatement(sql);
                stmt.setString(1,id);
                rs = stmt.executeQuery();
                if(rs.next()|old_password.equals(rs.getString("Teacher_password"))) { //判断密码是否正确
                    if (new_password.equals(confirm_password)) { //判断两次输入是否一致
                        stmt.close();
                        rs.close();
                        // 更新teacher表
                        String updateSql = "update teacher set Teacher_password = ? where Teacher_id = ?";
                        PreparedStatement stmt = conn.prepareStatement(updateSql);
                        stmt.setString(1, new_password);
                        stmt.setString(2, id);
                        stmt.executeUpdate();
                        // 回到初始登录界面
//                        RequestDispatcher view = request.getRequestDispatcher("Teacher.jsp");
//                        view.forward(request, response);
                        // 测试修改
                        backnews = "修改成功";
                        request.setAttribute("back",backnews);
                        RequestDispatcher view = request.getRequestDispatcher("Teacher.jsp");
                        view.forward(request, response);

                    }else{
                        backnews = "两次输入不相同";
                    }
                } else{
                    backnews = "原密码错误";
                }
                request.setAttribute("back",backnews);
                RequestDispatcher view = request.getRequestDispatcher("Teacher.jsp");
                view.forward(request, response);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

