<%@ page import="java.util.ArrayList" %>
<%@ page import="user.teacher.bean.TCourse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择进入的教学班级！</title>
    <style>
        body {
            font-family: sans-serif;
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
            padding-right: 2px;    <%-- 调整左右两边的间距--%>
        }
        .right {
            flex: 5;
        }
        <%-- .sidebar是设置左侧侧边栏区域的布局样式 --%>
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
        .sidebar .profile-name {
            font-size: 16px;
            font-family: sans-serif;
            margin-bottom: 5px;
        }
        .sidebar .profile-id {
            font-size: 16px;
            font-family: sans-serif;
            margin-bottom: 5px;
        }
        .sidebar a:hover {
            background-color: #f2f2f2;
        }
        <%--  设置右边显示区域的布局样式--%>
        .content-wrapper {
            flex: 1;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            margin-right: 20px;
            margin-top: 20px;
        }
        <%--  设置右边显示区域上方的布局样式--%>
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
        <%-- 右边显示区域的布局样式 --%>
        .content {
            flex: 1;
            padding: 20px;
            background-color: white;
        }
        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }
        .table-container th, .table-container td {
            padding: 10px;
            border: 1px solid cornflowerblue;
        }
        <%--对于超链接的标签文字的样式 --%>
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
            color: lightskyblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
        }
        <%-- 课程列表显示的布局样式--%>
        .course-table {
            margin: 0 auto;
            overflow-y: auto;
            border-collapse: collapse;
            width: 90%;
            text-align: center;
        }
        .course-table th, .course-table td {
            padding: 10px;
            text-align: center;
            border-bottom: 0.5px solid #ccc;      <%-- 使列表里的竖直线不显示  --%>
        }
        .course-table tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        .course-table tr:hover {
            background-color: #e0e0e0;
        }                                      <%-- 以上两个样式是让列表里的奇数行颜色深浅与偶数行不一样，且鼠标悬浮到奇数行颜色会改变 --%>
        .course-table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        th:first-child, td:first-child {
            border-left-width: 1px;
        }
        th:last-child, td:last-child {
            border-right-width: 1px;
        }
        tr:last-child th, tr:last-child td {
            border-bottom-width: 1px;
        }
    </style>
</head>
<jsp:useBean id="userBean" class="main.bean.User" scope="session"/>
<div class="container">
    <div class="left">
        <div class="horizontal-menu">
            <div class="sidebar">
                <div class="avatar"></div>
                <h3 class="profile-name" id="profile-name">
                    <jsp:getProperty name="userBean" property="name"/>
                </h3>
                <p class="profile-id" id="profile-id">
                    <jsp:getProperty name="userBean" property="logid"/>
                </p>
                <br><br>
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
                <div id="personal-info">
                    <label class="logout-button">我教的课>></label>
                    <%--                    <jsp:getProperty name="userBean" property="courseRes"/>--%>
                </div>
                <br><br>
                <%
                    ArrayList courselist=(ArrayList)request.getAttribute("courselist");
                %>
                <table align="center" class="course-table">
                    <tr>
                        <th>序号</th>
                        <th>课程名称</th>
                        <th>班级ID</th>
                        <th>上课时间</th>
                        <th>操作</th>
                    </tr>
                    <%--                        <form action="class" method="get">--%>
                    <%for(int i=0;i<courselist.size();i++){
                        TCourse tcourse=(TCourse) courselist.get(i);%>

                    <tr>
                        <td><%=i+1 %></td>   <!-- 修改这里，使用 i+1 来表示第几行 -->
                        <td><%=tcourse.getCourseName() %></td>
                        <% String classid = tcourse.getClasseId();%>
                        <td><%=classid %></td>
                        <td><%=tcourse.getCourseTime() %></td>
                        <td>
                            <a class="logout-button" href="/CourseManagement_war_exploded/class?classid=<%=tcourse.getClasseId()%>">
                                进入班级
                            </a>
                        </td>
                    </tr>
                    <% } %>
                </table>
                <br>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
</html>