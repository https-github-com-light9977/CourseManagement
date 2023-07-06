<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: yanyan
  Date: 2023/7/3
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" class="bean.Teacher" scope="session"/>
<html>
<head>
    <title>欢迎使用课程活动管理系统</title>
    <style>
        body {
            background-image: url(images/login_background.jpg);
            background-size: cover;
        }
        .container {
            position: relative;
            display: flex;
            justify-content: center;
            align-items: center;
            height:100vh;
            background-color: transparent;
        }
        .login-box {
            border-radius: 20px;
            width: 300px;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
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
            background-color: cornflowerblue;
            color: white;
            border: none;
            cursor: pointer;
        }
        .login-box input[type="submit"]:hover {
            background-color: cornflowerblue;
        }
        .error-message {
            color: red;
            font-weight: bold;
            display: none;
        }
    </style>
    <script>
        function showErrorMessage() {
            var errorMessage = document.getElementById("error-message");
            errorMessage.style.display = "block";
        }
    </script>
</head>
<body>
<div class="container">
    <div class="login-box">
        <div style="text-align: center;font-size: large"><h2>登录</h2></div>
        <form action="LoginServlet" method="post" onsubmit="showErrorMessage()">
            <%--@declare id="role"--%><label for="userid">账号:</label><input type="text" id="userid" name="userid" placeholder="请输入..." required><br>
            <br>
            <label for="password">密码:</label><input type="password" id="password" name="password" placeholder="请输入..." required><br><br>
            <br>
            <label for="role">身份:</label><br>
            <input type="radio" id="teacher" name="role" value="teacher">
            <label for="teacher">教师</label>
            <input type="radio" id="student" name="role" value="student" checked>
            <label for="student">学生</label><br><br>
            </select><br>
            <input type="submit" value="登录" >
        </form>
        <div
<%--                id="error-message" class="error-message"--%>
        >
            <%String backnews = (String)request.getAttribute("backnews");
                System.out.println(backnews);
                if (backnews != null){
                out.println(backnews);

                }
            %>
        </div>
    </div>
</div>
</body>
</html>
