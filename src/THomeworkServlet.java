import bean.TClass;
import bean.THomework;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

public class THomeworkServlet extends HttpServlet {
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = new TClass().getClassid();
        try {
//          //连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            //
            String sql = ""; //查询作业列表（作业id，作业要求，截止时间）语句
            //
            ResultSet hwRes = statement.executeQuery(sql);
            // hwRes，生成列表
            // 存入hwBean,发送至hwList.jsp
            THomework hwBean=null;
            HttpSession session=request.getSession(true);
            try {
                hwBean = (THomework) session.getAttribute("courseBean");
                if (hwBean == null) {
                    hwBean = new THomework();  //创建新的数据模型 。
                    session.setAttribute("loginBean", hwBean);
                    hwBean = (THomework) session.getAttribute("courseBean");
                }
                //在bean中放入resultSet
                hwBean.setHwRes(hwRes);
                //转发
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("hwList.jsp");//转发
                dispatcher.forward(request, response);
                con.close();
                statement.close();
                hwRes.close();
                }catch (Exception e){}


        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

