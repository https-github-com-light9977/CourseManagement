package main.servlet;

import main.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class ResetPasswordServlet extends HttpServlet {
    Connection conn;
    PreparedStatement stmt;
    ResultSet rs;
    String backnews = "test";
    Integer success = 0;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        User loginBean = null;
        HttpSession session = request.getSession(true);
        loginBean = (User) session.getAttribute("userBean");

        String id = loginBean.getLogid();
        String role = request.getParameter("role");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");
        boolean boo = (new_password.length() > 0) && confirm_password.length() > 0;
        if (role.equals("t")) {
        if (boo) {
            //连接数据库
//            conn = JdbcUtil.getConnection();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
                String user = "course_management2023";
                String db_password = "210470727czyCZY";
                conn = DriverManager.getConnection(url, user, db_password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //处理数据表
            try {
                String sql = "select * from teacher where Teacher_id = ? ";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                rs = stmt.executeQuery();

                if (rs.next() && old_password.equals(rs.getString("Teacher_password"))) { //判断密码是否正确
                    if (new_password.equals(confirm_password)) { //判断两次输入是否一致
                        if (new_password.equals(old_password)) {
                            backnews = "新旧密码一致，请重新输入";
                        } else {
                            stmt.close();
                            rs.close();
                            // 更新teacher表
                            String updateSql = "update teacher set Teacher_password = ? where Teacher_id = ?";
                            PreparedStatement stmt = conn.prepareStatement(updateSql);
                            stmt.setString(1, new_password);
                            stmt.setString(2, id);
                            stmt.executeUpdate();
                            // 回到初始登录界面
                            // 测试修改
                            backnews = "修改成功";
                            success = 1;
                            conn.close();
                        }
                    } else {
                        backnews = "两次输入不相同";
                    }
                } else {
                    backnews = "原密码错误";
                }

                if (success == 1) {
                    request.setAttribute("back", backnews);
                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);

                } else {
                    request.setAttribute("back", backnews);
                    RequestDispatcher view = request.getRequestDispatcher("Teacher.jsp");
                    view.forward(request, response);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
                backnews = "请输入新密码";
                request.setAttribute("back", backnews);
                RequestDispatcher view = request.getRequestDispatcher("Teacher.jsp");
                view.forward(request, response);
            }
        }


        else{
            if (boo) {
                //连接数据库
//            conn = JdbcUtil.getConnection();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
                    String user = "course_management2023";
                    String db_password = "210470727czyCZY";
                    conn = DriverManager.getConnection(url, user, db_password);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //处理数据表
                try {
                    String sql = "select * from student where Student_id = ? ";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, id);
                    rs = stmt.executeQuery();

                    if (rs.next() && old_password.equals(rs.getString("Student_password"))) { //判断密码是否正确
                        if (new_password.equals(confirm_password)) { //判断两次输入是否一致
                            if (new_password.equals(old_password)) {
                                backnews = "新旧密码一致，请重新输入";
                            } else {
                                stmt.close();
                                rs.close();
                                // 更新teacher表
                                String updateSql = "update student set Student_password = ? where Student_id = ?";
                                PreparedStatement stmt = conn.prepareStatement(updateSql);
                                stmt.setString(1, new_password);
                                stmt.setString(2, id);
                                stmt.executeUpdate();
                                // 回到初始登录界面
                                // 测试修改
                                backnews = "修改成功";
                                success = 1;
                                conn.close();
                            }
                        } else {
                            backnews = "两次输入不相同";
                        }
                    } else {
                        backnews = "原密码错误";
                    }

                    if (success == 1) {
                        request.setAttribute("back", backnews);
                        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                        view.forward(request, response);

                    } else {
                        request.setAttribute("back", backnews);
                        RequestDispatcher view = request.getRequestDispatcher("Student.jsp");
                        view.forward(request, response);

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                backnews = "请输入新密码";
                request.setAttribute("back", backnews);
                RequestDispatcher view = request.getRequestDispatcher("Student.jsp");
                view.forward(request, response);
            }



        }
    }
}



