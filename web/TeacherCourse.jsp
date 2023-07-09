<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.TCourse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择进入教学班级</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            height: 100%;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            display: flex;
            height: 100vh;
        }
        .left {
            flex: 1;
        }
        .right {
            flex: 5;
        }

        .sidebar {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            width: 150px;
            background-color: cornflowerblue;
            padding: 20px;
        }
        .sidebar .avatar {
            background-image: url(images/默认头像.jpg);
            background-size: cover;
            width: 100px;
            height: 100px;
            border-radius: 50%;
            margin-bottom: 10px;
        }
        .sidebar .profile-info {
            text-align: center;
        }
        .sidebar .profile-name {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .sidebar .profile-id {
            font-size: 14px;
        }
        .content-wrapper {
            flex: 1;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            margin-right: 20px;
            margin-top: 20px;
        }
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px;
            background-color: cornflowerblue;
            border-bottom: 1px solid #ffffff;
        }
        .logout-button {
            border: 2px solid #000;
            border-radius: 4px;
            padding: 10px 20px;
            background-color: cornflowerblue;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }
        .horizontal-menu {
            position: fixed;
            top: 0;
            bottom: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 10px;
            width: 150px;
            background-color: cornflowerblue;
            border-bottom: 1px solid #f2f2f2;
            float: left;
            border-radius: 10px;
            overflow: hidden;
        }
        .horizontal-menu a {
            margin-bottom: 10px;
            display: block;
            padding: 16px;
            text-decoration: none;
            color: black;
            transition: background-color 0.3s;
        }
        .sidebar a:hover {
            background-color: #f2f2f2;
        }
        .content {
            flex: 1;
            padding: 20px;
            background-color: white;
        }
        .label {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }
        .table-container th, .table-container td {
            padding: 10px;
            border: 1px solid cornflowerblue;
        }
        .form-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .form-container label {
            margin-bottom: 5px;
        }
        .form-container input {
            padding: 5px;
            margin-bottom: 10px;
            width: 200px;
        }
        .form-container button {
            padding: 10px 20px;
            background-color: cornflowerblue;
            border: none;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }
        a {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 10px;
            width: 150px;
            background-color: royalblue;
            border-bottom: 1px solid #f2f2f2;
            float: left;
            border-radius: 10px;
            overflow: hidden;
            font-size-adjust: unset;
            color: black; /* 设置超链接的文本颜色为蓝色 */
            text-decoration: none; /* 去除超链接的下划线 */
        }

        a:hover {
            color: royalblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
        }
        .course-table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 80%;
            text-align: center;
        }

        .course-table th, .course-table td {
            padding: 10px;
            border: 1px solid #ccc;
        }

        .course-table th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<jsp:useBean id="userBean" class="bean.User" scope="session"/>
<div class="container">
    <div class="left">
        <div class="horizontal-menu">
            <div class="sidebar">
                <div class="avatar"></div>
                <div class="profile-info">
                    <h3 class="profile-name" id="profile-name">
                        <jsp:getProperty name="userBean" property="name"/>
                    </h3>
                    <p class="profile-id" id="profile-id">
                        <jsp:getProperty name="userBean" property="logid"/>
                    </p>
                </div>
                <a href="/CourseManagement_war_exploded/course?id=1" class="a">课程活动>></a>
                <a href="Teacher.jsp" class="a">个人信息>></a>
            </div>
        </div>
    </div>
    <div class="right">
        <div class="content-wrapper">
            <div class="header">
                <button class="logout-button">退出空间</button>
            </div>
            <div class="content">
                <div id="password-form">
                    <a href="#" onclick="showTeacherSchedule()" class="a">我教的课>></a>
                    <%--                    <jsp:getProperty name="userBean" property="courseRes"/>--%>
                    <br><br><br><br>
                    <%
                        ArrayList courselist=(ArrayList)request.getAttribute("courselist");
                    %>
                    <h2 align="center">课程列表</h2>
                    <table align="center" class="course-table">
                        <tr>
                            <th>课程名称</th>
                            <th>班级ID</th>
                            <th>上课时间</th>
                            <th>操作</th>
                        </tr>
<%--                        <form action="class" method="get">--%>
                            <%for(int i=0;i<courselist.size();i++){
                            TCourse tcourse=(TCourse) courselist.get(i);%>
                        <tr><td><%=tcourse.getCourseName() %></td>
                            <% String classid = tcourse.getClasseId();%>
                            <td><%=classid %></td>
                            <td><%=tcourse.getCourseTime() %></td>
                            <td>
                                <a href="/CourseManagement_war_exploded/class?classid=<%=tcourse.getClasseId()%>">
                                <botton type="submit" class="submit-button" value="进入班级">进入班级</botton>
                                </a>
                            </td>
                        </tr>
                             </td>
                            </form>

                    <% } %>
                        <br>
                        <br>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
</script>
</body>
</html>