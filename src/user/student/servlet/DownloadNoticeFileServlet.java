package user.student.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.io.InputStream;

/**
 * Servlet implementation class GetDetails
 */
public class DownloadNoticeFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String noid = request.getParameter("noid") ;
        System.out.println(noid);
        ServletOutputStream sos;
        Connection con = null;
        PreparedStatement pstmt = null;

        sos = response.getOutputStream();

        ResultSet rset = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
                String user = "course_management2023";
                String db_password = "210470727czyCZY";
                con = DriverManager.getConnection(url, user, db_password);
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
            pstmt = con.prepareStatement("Select Notice_id,Notice_file,File_name from Notice where Notice_id=?");
            pstmt.setString(1, noid);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-disposition", "inline; filename*=UTF-8''" + URLEncoder.encode(rset.getString("File_name"), "UTF-8"));
                InputStream inputStream = rset.getBinaryStream("Notice_file");

                int i;
                while ((i = inputStream.read()) != -1) {
                    sos.write(i);
                }

                System.out.println(rset.getBytes("Notice_file"));
//                System.out.println(rset.getString("filename"));
            } else
                return;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (con != null) {
                // closes the database connection
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        sos.flush();
        sos.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
