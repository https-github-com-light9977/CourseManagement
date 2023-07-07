import bean.HwSubmitList;
import bean.THomework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bean.TCourse;
import unsolved.TClass;
import bean.THomework;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class HwSubmitListServlet extends HttpServlet {
        String class_id;
        String hw_id;
        public void doGet(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {
            request.setCharacterEncoding("utf-8");
            Connection con = null;
            Statement statement;
            hw_id = request.getParameter("hwid");
            class_id = request.getParameter("classid");
            try {
//          //连接数据库
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
                String user = "course_management2023";
                String db_password = "210470727czyCZY";
                con = DriverManager.getConnection(url, user, db_password);
                statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                //查询学生提交作业语句
                String sql = "select student.Student_id,Student_name,Grade" +
                        " from student,grade" +
                        " where student.Student_id=grade.Student_id and Homework_id='"+hw_id+"'";
                ResultSet hwsubmitRes = statement.executeQuery(sql);
                // 处理hwRes数据，生成列表并储存
                ArrayList<HwSubmitList> submitLists = new ArrayList<>();
                HwSubmitList hwsubmitlist ;
                while(hwsubmitRes.next()){

                    hwsubmitlist=new HwSubmitList();
                    hwsubmitlist.setHomework_id(hw_id);
                    hwsubmitlist.setStudent_id(hwsubmitRes.getString(1));
                    hwsubmitlist.setStudent_name(hwsubmitRes.getString(2));
                    hwsubmitlist.setGrade(hwsubmitRes.getString(3));
                    submitLists.add(hwsubmitlist);
                }
                request.setAttribute("submitLists", submitLists);
                hwsubmitRes.close();
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
                                request.getRequestDispatcher("THwSubmitList.jsp");//转发
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


