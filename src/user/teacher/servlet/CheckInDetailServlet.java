package user.teacher.servlet;

import user.teacher.bean.ManageStu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckInDetailServlet extends HttpServlet {
    String stuid;
    String stuname;
    String checkin_id;
    String checked = "未签到";
    String checkinGrade;
    String hwGrade;
    String class_id;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
        checkin_id = request.getParameter("checkin_id");
        System.out.println(checkin_id);

        ArrayList<ManageStu> manageStus = null;
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement();
            System.out.println("manage test");
            //查询学生列表
            String stusql = "select student.Student_id,Student_name" +
                    " from student,courseselection" +
                    " where student.Student_id=courseselection.Student_id and Class_id= '" + class_id + "'";
            ResultSet Res = statement.executeQuery(stusql);
            System.out.println("manage test");
            manageStus = new ArrayList<>();
            ManageStu manageStu;

            // 处理学生信息数据
            ArrayList<ArrayList<String>> stus = new ArrayList<>();
            while (Res.next()) {
                ArrayList<String> stu = new ArrayList<>();
                stuid = Res.getString(1);
                stuname = Res.getString(2);
                stu.add(stuid);
                stu.add(stuname);
                stus.add(stu);
            }
            Res.close();

            System.out.println(checkin_id);
            for (Integer i = 0; i < stus.size(); i++) {
                //通过班级ID 和 学生ID
                stuid = stus.get(i).get(0);
                stuname = stus.get(i).get(1);
                //获得签到数据
                System.out.println(stuid);
                System.out.println(checkin_id);
                String checksql = "select Student_id from checkinsituation where Student_id=? and CheckIn_id =?";
                PreparedStatement psmt = con.prepareStatement(checksql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
                psmt.setString(1,stuid);
                psmt.setString(2,checkin_id);
                ResultSet checkRes = psmt.executeQuery();
                if(checkRes.next()) {
                    System.out.println(checkRes.getString(1));
                    checked="已签到";
                }else {
                    checked ="未签到";
                }

                checkRes.close();

                System.out.println("test");
                manageStu = new ManageStu();
                manageStu.setStuid(stuid);
                manageStu.setStudent_name(stuname);
                manageStu.setCheckin(checked);
                manageStus.add(manageStu);
            }

            request.setAttribute("checkinDetail", manageStus);

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
                    RequestDispatcher dispatcher =
                            request.getRequestDispatcher("CheckInDetail.jsp");//转发
                    dispatcher.forward(request, response);
                    con.close();
                    statement.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ServletException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void solveCheckin(String class_id,String stuid){


    }

    public void solveHw(String class_id,String stuid){


    }

}

