package servlet.teacher;

import bean.*;
import servlet.student.bean.SCheckIn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageStuConServlet extends HttpServlet {
    String class_id;
    String stuid;
    String stuname;
    String checkin_id;
    String checked;
    String hwid;
    String grade;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        Connection con = null;
        Statement statement;
        class_id = request.getParameter("classid");
        stuid = request.getParameter("stuid");
        try {
//          //连接数据库
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);

            //查询学生姓名
            String stu_sql = "select Student_name from student where Student_id='" + stuid + "'";
            ResultSet stuRes = statement.executeQuery(stu_sql);
            stuRes.next();
            stuname = stuRes.getString(1);
            stuRes.close();

            //签到情况
            ArrayList<SCheckIn> sCheckIns = new ArrayList<>();
            SCheckIn sCheckIn;

            //查询班级所有发布的签到
            String checkin_sql = "select CheckIn_id from checkin where Class_id='" + class_id + "'";
            ResultSet checkRes = statement.executeQuery(checkin_sql);

            ArrayList<String> checks = new ArrayList<>();
            while (checkRes.next()) {
                checks.add(checkRes.getString(1));
            }
            checkRes.next();

            for (Integer i = 0; i < checks.size(); i++) {
                checkin_id = checks.get(i);
                //在每一次签到中查询学生id，查询到将checked赋值为已签到
                String checked_sql = "select * from checkinsituation where CheckIn_id='" + checkin_id + "' " +
                        "and Student_id = '" + stuid + "'";

                ResultSet checkedRes = statement.executeQuery(checked_sql);
                if (checkedRes.next()) {
                    checked = "已签到";
                }
                checkedRes.close();

                sCheckIn = new SCheckIn();
                sCheckIn.setStuid(stuid);
                sCheckIn.setStuname(stuname);
                sCheckIn.setCheckin_id(checkin_id.substring(13));
                sCheckIn.setChecked(checked);
                sCheckIns.add(sCheckIn);

            }

            request.setAttribute("sCheckIns", sCheckIns);
            checkRes.close();

            //作业情况
            ArrayList<Grade> grades = new ArrayList<>();
            Grade grade;
            //查询班级所有发布的作业
            String sql2 = "select Homework_id from homework where Class_id='"+class_id+"'";
            ResultSet hwRes = statement.executeQuery(sql2);
            ArrayList<String> homeworks = new ArrayList<>();
            while(hwRes.next()) {
                homeworks.add(hwRes.getString(1));
            }
            hwRes.close();

            for (Integer i = 0; i < homeworks.size(); i++) {
                hwid = homeworks.get(i);
                //在每一次作业中查询学生id和成绩，若无法查询到学生id则成绩为0
                String hw_sql = "select * from grade where Homework_id='" + hwid + "' " +
                        "and Student_id = '" + stuid + "'";

                ResultSet hwgradeRes = statement.executeQuery(hw_sql);
                if (!hwgradeRes.next()) {
                    this.grade = "0";
                }else{
                    this.grade = hwgradeRes.getString(3);
                }
                hwgradeRes.close();


                grade =new Grade();
                grade.setHwid(hwid.substring(8));
                grade.setGrade(this.grade);
                grades.add(grade);
            }
            request.setAttribute("sHwGrades", grades);


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
                            request.getRequestDispatcher("ManageStuCon.jsp");//转发
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


}

