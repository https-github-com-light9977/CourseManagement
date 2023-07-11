package user.teacher.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
@MultipartConfig
public class SubmitNoticeServlet extends HttpServlet {
    String no_id;
    String content;
    String time;
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Connection con = null;
        Statement statement;
        content = request.getParameter("content");
        time = request.getParameter("publishTime");
        class_id = request.getParameter("classid");
        InputStream fileInputStream=null;//文件输入流
        Part part=request.getPart("file");//获取表单提交的文件
        String filename = part.getSubmittedFileName();
        try {
            System.out.println(part.getContentType());
            if (part!=null){
                System.out.println(part.getContentType());
                fileInputStream= part.getInputStream(); }
//
// 连接数据库
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
            // 插入数据
            String insertsql = "insert into notice(Class_id,Content,Notice_id,NoticeTime,Notice_file,File_name) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertsql);
            preparedStatement.setString(1,class_id);

            if(content.length()>20){content = content.substring(0,20);}
            preparedStatement.setString(2,content);
            preparedStatement.setString(3,no_id);

            //获取发布时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            time = df.format(System.currentTimeMillis());
            System.out.println(df.format(System.currentTimeMillis()));


            preparedStatement.setString(4,time);
            System.out.println("insert notice");
            preparedStatement.setBinaryStream(5,fileInputStream);
            preparedStatement.setString(6,filename);
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



