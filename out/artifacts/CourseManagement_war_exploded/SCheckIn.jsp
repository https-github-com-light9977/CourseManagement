<%@ page import="java.util.ArrayList" %>
<%@ page import="user.teacher.bean.THomework" %>
<%@ page import="main.bean.CheckIn" %>
<%@ page import="java.util.List" %>
<%@ page import="user.student.bean.SCheckIn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到</title>
    <style>
        body {
            font-family:sans-serif;
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
            padding-right: 2px;
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
        .sidebar .profile-name {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .sidebar .profile-id {
            font-size: 14px;
        }
        .sidebar a:hover {
            background-color: #f2f2f2;
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
        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }
        .table-container th, .table-container td {
            padding: 10px;
            border: 1px solid cornflowerblue;
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
            color: lightskyblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
        }
        .choiceheader {
            margin: 0 10px;
            text-decoration: none;
            color: #000;
            background-color: white;
            position: relative;
            display: inline-block;
            transition: all 0.3s ease;
        }
        .choiceheader.active {
            text-decoration: underline;
        }
        .choicecontent {
            padding: 20px;
            border-top: 2px solid #000;
        }
        .checkin-table {
            margin: 0 auto;
            overflow-y: auto;
            border-collapse: collapse;
            width: 90%;
            text-align: center;
        }
        .checkin-table th, .checkin-table td {
            padding: 10px;
            text-align: center;
            border: 0.5px solid #ccc;
        }
        .checkin-table {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .checkin-table tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        .checkin-table tr:hover {
            background-color: #e0e0e0;
        }                                      <%-- 以上两个样式是让列表里的奇数行颜色深浅与偶数行不一样，且鼠标悬浮到奇数行颜色会改变 --%>
        .checkin-table th {
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
        .content {
            flex: 1;
            padding: 20px;
            background-color: white;
        }
    </style>
</head>
<body>
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
                <a href="/CourseManagement_war_exploded/scourse?stuid=<%=userBean.getLogid()%>" class="a">课程活动>></a>
                <a href="Student.jsp" class="a">个人信息>></a>
            </div>
        </div>
    </div>
    <div class="right">
        <div class="content-wrapper">
            <div class="header">
                <a href="/CourseManagement_war_exploded">
                    <button class="logout-button">退出空间</button>
                </a>
            </div>
            <br>
            <div class="logout-button"
            <%
                List classinfo=(List)request.getAttribute("classinfo");
            %>
            <%for(int i=0;i<classinfo.size();i++){%>
            <td><%=classinfo.get(i)%></td>
            <%}%>
        </div>
        <br>
        <div>
            <a class="choiceheader" href="/CourseManagement_war_exploded/shomework?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">作业</a>
            <a class="choiceheader" href="/CourseManagement_war_exploded/scheckin?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">签到</a>
            <a class="choiceheader" href="/CourseManagement_war_exploded/snotice?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">通知</a>
<%--            <a class="choiceheader" href="/CourseManagement_war_exploded/sgrade?classid=<%=classinfo.get(0)%>&stuid<%=userBean.getLogid()%>">查看成绩</a>--%>
        </div>
        <br>
        <div class="choicecontent"></div>
        <div class="content">
            <div>
                <label class="logout-button">签到列表>></label>
            </div>
            <br><br>
                <table align="center" class="checkin-table">
                    <tr>
                        <th>序号</th>
                        <th>签到ID</th>
                        <th>截至时间</th>
                        <th>签到状态</th>
                    </tr>
                    <%
                        ArrayList checkInArrayList=(ArrayList)request.getAttribute("checkInArrayList");
                    %>
                    <%for(int i=0;i<checkInArrayList.size();i++){
                        SCheckIn scheckIn=(SCheckIn) checkInArrayList.get(i);%>
                    <tr>
                        <td><%=i+1 %></td>
                        <td><%=scheckIn.getCheckin_id() %></td>
                        <td><%=scheckIn.getCheckin_deadline() %></td>
                        <td><%=scheckIn.getChecked()%></td>
                        <td><a class="logout-button" href="/CourseManagement_war_exploded/subcheckin?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>&checkin_id=<%=scheckIn.getCheckin_id()%>">
                            签到
                        </a>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>

        </div>
    </div>
</div>
</body>
</html>
