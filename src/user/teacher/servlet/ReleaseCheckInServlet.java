package user.teacher.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReleaseCheckInServlet extends HttpServlet {
    String checkin_id;
    String time;
    String class_id;
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
        System.out.println("checkin");
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023?allowMultiQueries=true";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);

            //查询签到是否为空，设置通知ID
            String condition1 = "select  count(*) as cnt from checkin where Class_id='" + class_id + "'";
            String condition2 = "select Checkin_id from checkin where Class_id='" + class_id + "'";
            ResultSet noticeExist = statement.executeQuery(condition1);
            noticeExist.next();
            System.out.println("checkin");
            System.out.println(noticeExist.getString("cnt"));
            if("0".equals(noticeExist.getString("cnt"))){
                checkin_id = class_id +"check"+ "01";
                noticeExist.close();
            }else{
                noticeExist.close();
                System.out.println("checkin");
                ResultSet getLast = statement.executeQuery(condition2);
                System.out.println("checkin");
                getLast.last();
                String lastid;
                lastid = getLast.getString(1);
                System.out.println(lastid);
                String lastnum = lastid.substring(13);
                System.out.println(lastnum);
                if(Integer.parseInt(lastnum)<9){
                    checkin_id = class_id + "check0" + (Integer.parseInt(lastnum)+1);
                }else {
                    checkin_id = class_id +"check"+ (Integer.parseInt(lastnum)+1);
                }
                getLast.close();
            }
            System.out.println("insert checkin");
            // 插入数据
            String insertsql = "insert into checkin(CheckIn_id,Class_id,CheckIn_deadline) values(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertsql);
            preparedStatement.setString(1,checkin_id);
            preparedStatement.setString(2,class_id);


            //签到截至时间，5分钟
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.add(Calendar.MINUTE,5);
            Date time = instance.getTime();
            String checkinTime = df.format(time);

//            String checkinTime = df.format(System.currentTimeMillis());
            System.out.println(checkinTime);

            preparedStatement.setString(3, checkinTime);
            preparedStatement.executeUpdate();

            //转发
//            request.setAttribute("grade",grade);
//            RequestDispatcher dispatcher =
//                    request.getRequestDispatcher("THwDetails.jsp");
//            dispatcher.forward(request, response);
            System.out.println("转发");
            String redirect_url ="/CourseManagement_war_exploded/checkin?" +
                    "classid="+class_id;
            response.sendRedirect(redirect_url);
            System.out.println("转发");
        }catch (Exception e){}
    }

}



