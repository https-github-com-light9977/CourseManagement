package user.teacher.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@MultipartConfig
public class SubmitReleaseHwServlet extends HttpServlet {
    String class_id;
    String hwid;
    String hw_requirement;
    String deadline;
    String releaseTime;
    String grouped;
    String backnews;
    String groupid;
    String submit;
    InputStream fileInputStream=null;
    public void service(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Connection con = null;
        Statement statement;
        //文件输入流
        Part part=request.getPart("file");//获取表单提交的文件
        String filename = part.getSubmittedFileName();

        class_id = request.getParameter("classid");
        releaseTime = request.getParameter("publishTime");
        deadline = request.getParameter("deadline");
        System.out.println(deadline);
        grouped = request.getParameter("group");
        hw_requirement = request.getParameter("content");
        if(hw_requirement ==null){hw_requirement = "";}
        backnews = request.getParameter("backnews");
        if(grouped.equals("y")&&backnews.length()==0){
            request.setAttribute("hint","请先完成分组");
            request.setAttribute("classid",class_id);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("ReleaseHw.jsp");//转发
            dispatcher.forward(request, response);
        }

        try {
            System.out.println(part.getContentType());
            if (part!=null){
                System.out.println(part.getContentType());
                fileInputStream= part.getInputStream(); }
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023?allowMultiQueries=true";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
            //查询作业列表是否为空,设置hwid
            String condition1 = "select  count(*) as cnt from homework where Class_id='" + class_id + "'";
            String condition2 = "select Homework_id from homework where Class_id='" + class_id + "'";
            ResultSet hwExist = statement.executeQuery(condition1);
            hwExist.next();
            System.out.println("res test");
            if("0".equals(hwExist.getString("cnt"))){
                hwid = class_id + "01";
                hwExist.close();
            }else{
                ResultSet getLast = statement.executeQuery(condition2);
                getLast.last();
                String lasthwid;
                lasthwid = getLast.getString(1);
                System.out.println(lasthwid);
                String lastnum = lasthwid.substring(8);
                    if(Integer.parseInt(lastnum)<9){
                        hwid = class_id + "0" + (Integer.parseInt(lastnum)+1);
                    }else {
                        hwid = class_id + (Integer.parseInt(lastnum)+1);
                    }
                getLast.close();
            }

            System.out.println("insert");
            // 插入数据
            String insertsql = "insert into homework(Homework_id,Class_id,ReleaseTime,Homework_Deadline,Grouped,Request,Hw_file,File_name) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertsql);
            preparedStatement.setString(1,hwid);
            preparedStatement.setString(2,class_id);

            //获取发布时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            releaseTime = df.format(System.currentTimeMillis());
            System.out.println(df.format(System.currentTimeMillis()));

            preparedStatement.setString(3,releaseTime);
            preparedStatement.setString(4,deadline);
            preparedStatement.setString(5,grouped);
            hw_requirement =hw_requirement.replace("\r","<br />");
            preparedStatement.setString(6,hw_requirement);
            preparedStatement.setBinaryStream(7,fileInputStream);
            preparedStatement.setString(8,filename);
            preparedStatement.executeUpdate();
            System.out.println("insert");

            //班级信息
            String s = "select Class_id,Class_name,Course_time,Location from course where Class_id='" + class_id + "'";   //返回(班级id,班级名称,上课时间,地点)查询语句
            ResultSet classRes = statement.executeQuery(s);
            //解析classRes
            if (classRes.next()) {

                String classname = classRes.getString(2);
                String classtime = classRes.getString(3);
                String location = classRes.getString(4);
                try {
                    //将结果打包成list传入前端
                    List<String> classinfo = new ArrayList<>();
                    classinfo.add(class_id);
                    classinfo.add(classname);
                    classinfo.add(classtime);
                    classinfo.add(location);
                    request.setAttribute("classinfo", classinfo);

                    //转发
//                    RequestDispatcher dispatcher =
//                            request.getRequestDispatcher("THomeWork.jsp");//转发
//                    dispatcher.forward(request, response);
                    String redirect_url ="/CourseManagement_war_exploded/homework?" +
                            "classid="+class_id;
                    response.sendRedirect(redirect_url);
                    System.out.println("转发");

                    con.close();
                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

