package servlet.teacher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class SubmitNoticeServlet extends HttpServlet {
    String no_id;
    String content;
    String time;
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        content = request.getParameter("content");
        time = request.getParameter("publishTime");
        class_id = request.getParameter("classid");
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023?allowMultiQueries=true";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            //查询通知是否为空，设置通知ID
            String condition1 = "select  count(*) as cnt from notice where Class_id='" + class_id + "'";
            String condition2 = "select Notice_id from notice where Class_id='" + class_id + "'";
            ResultSet noticeExist = statement.executeQuery(condition1);
            noticeExist.next();
            if("0".equals(noticeExist.getString("cnt"))){
                no_id = class_id +"-"+ "1";
                noticeExist.close();
            }else{

                ResultSet getLast = statement.executeQuery(condition2);
                System.out.println(getLast.last());
                String last_noid;
                last_noid = getLast.getString("Notice_id");
                no_id = class_id + "-"+((Integer.parseInt(last_noid.substring(9)))+1);
                getLast.close();
            }
            System.out.println("insert notice");
            // 插入数据
            String insertsql = "insert into notice(Class_id,Content,Notice_id,NoticeTime) values(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertsql);
            preparedStatement.setString(1,class_id);
            if(content.length()>20){content = content.substring(0,20);}
            preparedStatement.setString(2,content);
            preparedStatement.setString(3,no_id);
            preparedStatement.setString(4,time);
            preparedStatement.executeUpdate();
            System.out.println("insert");
            //转发
//            request.setAttribute("grade",grade);
//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("THwDetails.jsp");
//            dispatcher.forward(request, response);
            String redirect_url ="/CourseManagement_war_exploded/notice?" +
                    "classid="+class_id;
            response.sendRedirect(redirect_url);
            System.out.println("转发");
        }catch (Exception e){}
    }

}



