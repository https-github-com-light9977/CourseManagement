<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" class="bean.User" scope="session"/>
<html>
<title>欢迎使用课程活动管理系统!请登录</title>
<style>
    <%-- body是全部背景的样式--%>
    body {
        background-image: url(images/login_background.jpg);
        background-size: cover;
    }
    <%-- conrainer是设置登录框位置的样式--%>
    .container {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
        height:100vh;
        background-color: transparent;
    }
    <%-- login-box是登录框的样式--%>
    .login-box {
        border-radius: 20px;
        width: 300px;
        padding: 20px;
        position: relative;
        z-index: 1;
        background-color: rgba(255, 255, 255, 0.5); <%--  这3句通过调整RGBA颜色值中的透明度值（0到1之间的值），可以改变登录框的透明度--%>
        box-shadow: 0 10px 10px rgba(0, 0, 0, 0.8);
        margin-bottom: 20px;
    }
    .login-title {
        font-family: Sans-serif;
        text-align: center; <%--将文本在水平方向上居中对齐 --%>
        display: flex;
        justify-content: center;
    }
    <%-- 这是登录框内输入账号和密码的输入框的样式--%>
    .login-box input[type="text"],
    .login-box input[type="password"] {
        border-radius:20px;
        width:100%;
        padding:10px;
        margin-bottom:10px;
    }
    <%-- 这是登录框内登录按钮未被用户点击时的样式--%>
    .login-box input[type="submit"] {
        width:100%;
        padding:10px;
        background-color: cornflowerblue;
        color: white;
        border: none;
        cursor: pointer;
    }
    <%-- 这是登录框内鼠标悬浮在登录按钮上的时候的样式--%>
    .login-box input[type="submit"]:hover {
        background-color: cornflowerblue;
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
        <div class="login-title"><h2>登录</h2></div>
        <br>
        <form action="LoginServlet" method="post" onsubmit="showErrorMessage()">
            <%--@declare id="role"--%>
            <label for="userid">账号:</label><br><input type="text" id="userid" name="userid" placeholder="请输入..." required><br>
            <br>
            <label for="password">密码:</label><br><input type="password" id="password" name="password" placeholder="请输入..." required><br><br>
            <br>
            <label for="role">身份:</label><br>
            <input type="radio" id="teacher" name="role" value="teacher">
            <label for="teacher">教师</label>
            <input type="radio" id="student" name="role" value="student" checked>
            <label for="student">学生</label><br><br>
            </select><br>
            <input type="submit" value="登录" >
        </form>
        <div>
            <%String backnews = (String)request.getAttribute("backnews");
                System.out.println(backnews);
                if (backnews != null){
                    out.println(backnews);
                }
            %>
            <a href="http://localhost:8080/CourseManagement_war_exploded/randomGroup?classid=FEG40901">test</a>
        </div>
    </div>
</div>
</body>
</html>
