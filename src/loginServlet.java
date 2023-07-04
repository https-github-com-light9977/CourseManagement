import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import javax.sql.DataSource;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
public class loginServlet extends HttpServlet{
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
        password = Encrypt.encrypt(password,"javajsp");//给用户密码加密。
        boolean boo=(logid.length()>0)&&(password.length()>0);
        try{
//            Context  context = new InitialContext();
//            Context  contextNeeded=(Context)context.lookup("java:comp/env");
//            DataSource ds=
//                    (DataSource)contextNeeded.lookup("mobileConn");//获得连接池。
//            con= ds.getConnection();//使用连接池中的连接。
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://rm-cn-pe33aabsn000o2io.rwlb.cn-chengdu.rds.aliyuncs.com:3306/course_management-2023";
            String user = "course_management2023";
            String db_password = "210470727czyCZY";
            con = DriverManager.getConnection(url, user, db_password);
            if(identity.equals("教师")) {
                String condition_1 = "select * from teacher where Teacher_id = '" + logid + "'";  //判断用户id是否存在
                String condition_2 = "select * from user where logname = '"+logid+
                        "' and password ='"+password+"'";
                sql = con.createStatement();
                if (boo) {
                    ResultSet rs_1 = sql.executeQuery(condition_1);
                    ResultSet rs_2 = sql.executeQuery(condition_2);
                    boolean m1 = rs_1.next();
                    boolean m2 = rs_2.next();
                    if (m1 == true) {         //判断用户id是否正确
                        if (m2 == true) {
                            success(request, response, logid);
                            RequestDispatcher dispatcher =
                                    request.getRequestDispatcher("teacher.jsp");//转发
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
                con.close();//连接返回连接池。
            }
        }
        catch(SQLException exp){
            String backNews=""+exp;
            fail(request,response,backNews);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            try{
                con.close();
            }
            catch(Exception ee){}
        }
    }
    public void success(HttpServletRequest request,
                        HttpServletResponse response,
                        String logid) {
        Login loginBean=null;
        HttpSession session=request.getSession(true);
        try{  loginBean=(Login)session.getAttribute("loginBean");
            if(loginBean==null){
                loginBean=new Login();  //创建新的数据模型 。
                session.setAttribute("loginBean",loginBean);
                loginBean=(Login)session.getAttribute("loginBean");
            }

            String id =loginBean.getLogid();
            if(id.equals(logid)) {
                loginBean.setLogid(logid);
            }
            else {  //数据模型存储新的登录用户:
                loginBean.setLogid(logid);
            }
            // 根据用户id从数据库查找姓名，并保存在loginBean中
            Class.forName("com.mysql.jdbc.Driver");
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

        }
        catch(Exception ee){
            // 报错处理
//            loginBean=new Login();
//            session.setAttribute("loginBean",loginBean);
//            loginBean.setBackNews(ee.toString());
//            loginBean.setLogid(logid);
        }
    }
    public void fail(HttpServletRequest request,
                     HttpServletResponse response,String backNews) {
        response.setContentType("text/html;charset=utf-8");
        try {

            //失败操作
            //清空所有输入数据并返回报错结果
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+""+backNews+"</h2>") ;
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
