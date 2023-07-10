package servlet.teacher.servlet;

import bean.Group;
import servlet.teacher.dao.GroupDao;

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

public class RandomGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int groupSize = Integer.parseInt(request.getParameter("groupSize"));
        int numGroups = Integer.parseInt(request.getParameter("number"));
        String classid = request.getParameter("classid");
        // 连接数据库
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String newhwid = request.getParameter("newhwid");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            conn = DriverManager.getConnection(url, user, db_password);
            System.out.println(classid);
            // 查询学生名单
            String sql = "SELECT student.Student_id,Student_name FROM student,courseselection where student.Student_id = courseselection.Student_id and Class_id ='"+classid+"'"; // 替换为你的学生名单表名
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
            rs = stmt.executeQuery(sql);

            List<String> student = new ArrayList<>();
            while (rs.next()) {
                String stuid = rs.getString("Student_id");
                student.add(stuid);
            }
            rs.close();

            System.out.println(student.size());
            // 随机分组
            Collections.shuffle(student);
            int groupSize = student.size() / numGroups;
            System.out.println(numGroups);
            System.out.println(groupSize);
            List<List<String>> groups = new ArrayList<>();
            int cnt=0;
            for (int i = 0; i < numGroups; i++) {
                List<String> temp = student.subList(i * groupSize, (i + 1) * groupSize);
                List<String> group = new ArrayList<String>(temp);
                groups.add(group);
                cnt = cnt + groupSize;
            }
            System.out.println(cnt);
            if (cnt<student.size()){
                int j=0;
                for(int i = cnt;i< student.size();i++){
                    groups.get(j).add(student.get(i));
                    j++;
                }
            }

            System.out.println(groups);

            ArrayList<Group> groupLst = new ArrayList<>();
            Group sgroup ;
            for(int i = 0;i<groups.size();i++){
                for(int j = 0 ;j<groups.get(i).size();j++){
                    sgroup = new Group();
                    sgroup.setGroupid(String.valueOf(i+1));
                    sgroup.setStuid(groups.get(i).get(j));
                    //放入学生名字
                    String stuid = groups.get(i).get(j);
                    ResultSet rs2 = stmt.executeQuery("select Student_name from student where Student_id = '"+stuid+"'");
                    rs2.next();
                    String stuname =rs2.getString(1);
                    sgroup.setStuname(stuname);
                    groupLst.add(sgroup);
                }
            }

            new GroupDao().insertGroups(classid,groupLst,newhwid);//插入数据库

            request.setAttribute("newhwid",newhwid);
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