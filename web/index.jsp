<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: yanyan
  Date: 2023/7/3
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="bean.Login" scope="session"/>
<html>
<head>
    <title>欢迎使用课程活动管理系统</title>
    <style>
        body {
            background-image: url(web/images/login_background.jpg);
            background-size: cover;
        }
        .container {
            display: flex;
            justify-content: right;
            align-items: center;
            height:100vh;
            padding-right:15%;
            background-color: #f1f1f1;
        }
        .login-box {
            border-radius:20px;
            width:300px;
            padding:20px;
            background-color: white;
            box-shadow:0010px rgba(0,0,0,0.1);
        }
        .login-box input[type="text"],
        .login-box input[type="password"] {
            border-radius:20px;
            width:100%;
            padding:10px;
            margin-bottom:10px;
        }
        .login-box input[type="submit"] {
            width:100%;
            padding:10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .login-box input[type="submit"]:hover {
            background-color: cornflowerblue;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-box">
        <div style="text-align: center;"><h2>登录</h2></div>

        <form action="LoginServlet" method="post">
            <%--@declare id="role"--%><label for="userid">账号:</label>
            <input type="text" id="userid" name="userid" placeholder="请输入..." required><br>
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" placeholder="请输入..." required><br><br>
            <label for="role">身份:</label><br>
            <input type="radio" id="teacher" name="role" value="teacher">
            <label for="teacher">教师</label>
            <input type="radio" id="student" name="role" value="student" checked>
            <label for="student">学生</label><br><br>
            </select><br>
                <jsp:getProperty name="loginBean" property="backNews"/><br>
            <input type="submit" value="登录" >
        </form>
    </div>
</div>
</body>
<%--<% String msg = (String)request.getAttribute("msg");--%>
<%--    if (msg != null) { %>--%>
<%--<script>alert("<%=msg%>")</script>--%>
<%--<% } %>--%>


</html>