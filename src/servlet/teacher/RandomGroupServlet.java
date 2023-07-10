package servlet.teacher;

import bean.Group;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RandomGroupingServlet")
public class RandomGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int groupSize = Integer.parseInt(request.getParameter("groupSize"));
        int groupSize = 3;
        String classid = request.getParameter("classid");
        // 连接数据库
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            conn = DriverManager.getConnection(url, user, db_password);
            System.out.println(classid);
            // 查询学生名单
            String sql = "SELECT student.Student_id,Student_name FROM student,courseselection where student.Student_id = courseselection.Student_id and Class_id ='"+classid+"'"; // 替换为你的学生名单表名
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            List<String> student = new ArrayList<>();
            while (rs.next()) {
                System.out.println("test");
                String stuname = rs.getString("Student_name");
                student.add(stuname);
            }

            System.out.println(student);
            // 随机分组
            Collections.shuffle(student);
            int numGroups = student.size() / groupSize;

            List<List<String>> groups = new ArrayList<>();
            for (int i = 0; i < numGroups; i++) {
                List<String> group = student.subList(i * groupSize, (i + 1) * groupSize);
                groups.add(group);
            }

            System.out.println(groups);

            ArrayList<Group> groupLst = new ArrayList<>();
            Group sgroup ;
            for(int i = 0;i<groups.size();i++){
                for(int j = 0 ;j<groups.get(i).size();j++){
                    sgroup = new Group();
                    sgroup.setGroupid(String.valueOf(i+1));
                    sgroup.setStuname(groups.get(i).get(j));
                    groupLst.add(sgroup);
                }
            }

            request.setAttribute("classid",classid);
            request.setAttribute("groups", groupLst);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("TGroup.jsp").forward(request, response);
    }
}