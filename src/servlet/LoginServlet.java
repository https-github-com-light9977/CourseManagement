package servlet;

import bean.Teacher;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import javax.sql.DataSource;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
public class LoginServlet extends HttpServlet{
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }
    public void service(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        Connection con =null;
        Statement sql;
        // 关键字
        String identity = request.getParameter("role"); // 身份变量
        String logid=request.getParameter("userid").trim(), //登录id
                password=request.getParameter("password").trim(); //密码
//        password = unsolved.Encrypt.encrypt(password,"javajsp");//给用户密码加密。

        boolean boo=(logid.length()>0)&&(password.length()>0);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);

            if(identity.equals("teacher")) {
                String condition_1 = "select * from teacher where Teacher_id = '" + logid + "'";  //判断用户id是否存在
                String condition_2 = "select * from teacher where Teacher_id = '"+logid+
                        "' and Teacher_password ='"+password+"'";
                sql = con.createStatement();

                if (boo) {
                    ResultSet rs_1 = sql.executeQuery(condition_1);
                    boolean m1 = rs_1.next();

                    if (m1 == true) {//判断用户id是否正确
                        rs_1.close();
                        ResultSet rs_2 = sql.executeQuery(condition_2);
                        boolean m2 = rs_2.next();
                        if (m2 == true) { //判断密码是否正确
                            success1(request, response, logid,password);
                            rs_2.close();
                            RequestDispatcher dispatcher =
                                    request.getRequestDispatcher("Teacher.jsp");//转发
                            dispatcher.forward(request, response);
                        }else{
                            String backNews = "您输入的密码不正确,请重新输入";
                            System.out.println(backNews);
                            //调用登录失败的方法:
                            fail(request, response, backNews);
                        }
                    } else {
                        String backNews = "您输入的Id不正确,请重新输入";
                        System.out.println(backNews);
                        //调用登录失败的方法:
                        fail(request,response,backNews);
                    }
                } else {
                    String backNews = "请输入用户名和密码";
                    //调用登录失败的方法:
                    fail(request, response, backNews);
                }
                con.close();
                sql.close();
                //连接返回连接池。
            }
            else{
                String condition_1 = "select * from student where Student_id = '" + logid + "'";  //判断用户id是否存在
                String condition_2 = "select * from student where Student_id = '"+logid+
                        "' and Student_password ='"+password+"'";//判断密码是否正确
                sql = con.createStatement();

                if (boo) {
                    ResultSet rs_1 = sql.executeQuery(condition_1);
                    boolean m1 = rs_1.next();
                    if (m1 == true) {//判断用户id是否正确
                        rs_1.close();
                        ResultSet rs_2 = sql.executeQuery(condition_2);
                        boolean m2 = rs_2.next();

                        if (m2 == true) { //判断密码是否正确
                            success2(request, response, logid,password);
                            RequestDispatcher dispatcher =
                                    request.getRequestDispatcher("Student.jsp");//转发
                            dispatcher.forward(request, response);
                        }else{
                            String backNews = "您输入的密码不正确,请重新输入";
                            //调用登录失败的方法:
                            fail(request, response, backNews);
                        }
                    } else {
                        String backNews = "您输入的Id不正确,请重新输入";
                        //调用登录失败的方法:
                        fail(request, response,backNews);
                    }
                } else {
                    String backNews = "请输入用户名和密码";
                    //调用登录失败的方法:
                    fail(request, response, backNews);
                }
                con.close();
                sql.close();
                //连接返回连接池。

            }
        }
        catch(SQLException exp){
            exp.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                con.close();
            }
            catch(Exception ee){}
        }
    }


    public void success1(HttpServletRequest request,
                        HttpServletResponse response,
                        String logid,String password) {
        Teacher loginBean=null;
        HttpSession session=request.getSession(true);
        try{
            loginBean=(Teacher)session.getAttribute("userBean");
            if(loginBean==null) {
                loginBean = new Teacher();  //创建新的数据模型 。
                session.setAttribute("userBean", loginBean);
                loginBean = (Teacher) session.getAttribute("userBean");
            }
            loginBean.setBackNews("登录成功");
            System.out.println(loginBean.getBackNews());
            String id =loginBean.getLogid();
            if(id.equals(logid)) {
                loginBean.setLogid(logid);
            }
            else {  //数据模型存储新的登录用户:
                loginBean.setLogid(logid);
            }

            // 根据用户id从数据库查找姓名，并保存在loginBean中
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            Connection connection = DriverManager.getConnection(url, user, db_password);
            Statement statement = null;
            statement = connection.createStatement();
            String findName = "select Teacher_name from teacher where Teacher_id = '" + logid + "'";
            ResultSet nameSet = statement.executeQuery(findName);
            boolean n = nameSet.next();
            if(n){
                String name = nameSet.getString("Teacher_name");
                loginBean.setName(name);
            }else{
                //报错处理
            }
            connection.close();
            statement.close();
            nameSet.close();

        }
        catch(Exception ee){
            // 报错处理
        }
    }

    public void success2(HttpServletRequest request,
                         HttpServletResponse response,
                         String logid,String password) {
        Teacher loginBean=null;
        HttpSession session=request.getSession(true);
        try{  loginBean=(Teacher)session.getAttribute("userBean");
            if(loginBean==null){
                loginBean=new Teacher();  //创建新的数据模型 。
                session.setAttribute("userBean",loginBean);
                loginBean=(Teacher)session.getAttribute("userBean");
            }
            loginBean.setBackNews(" ");
            String id =loginBean.getLogid();
            if(id.equals(logid)) {
                loginBean.setLogid(logid);
            }
            else {  //数据模型存储新的登录用户:
                loginBean.setLogid(logid);
            }
            // 根据用户id从数据库查找姓名，并保存在loginBean中
            loginBean.setBackNews("登录成功");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            Connection connection = DriverManager.getConnection(url, user, db_password);
            Statement statement = null;
            statement = connection.createStatement();
            String findName = "select Student_name from student where Student_id = '" + logid + "'";
            ResultSet nameSet = statement.executeQuery(findName);
            boolean n = nameSet.next();
            if(n){
                String name = nameSet.getString("Student_name");
                loginBean.setName(name);
            }else{
                //报错处理
            }
            connection.close();
            statement.close();
            nameSet.close();

        }
        catch(Exception ee){
            // 报错处理
        }
    }

    public void fail(HttpServletRequest request,
                     HttpServletResponse response,String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {
//            Teacher loginBean = null;
//            HttpSession session = request.getSession(true);
//            try {
//                loginBean = (Teacher) session.getAttribute("userBean");
//                if (loginBean == null) {
//                    loginBean = new Teacher();  //创建新的数据模型 。
//                    session.setAttribute("userBean", loginBean);
//                    loginBean = (Teacher) session.getAttribute("userBean");
//                }
//                loginBean.setBackNews(backNews);
//                System.out.println(loginBean.getBackNews());
                request.setAttribute("backnews",backNews);
                System.out.println(backNews);
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("index.jsp");//转发
                dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
