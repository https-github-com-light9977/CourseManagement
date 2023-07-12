<%@ page import="java.util.ArrayList" %>
<%@ page import="user.teacher.bean.TCourse" %>
<%@ page import="main.bean.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分组！</title>
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
            border: 0.5px solid #ccc;
        }
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
            <a class="logout-button" href="/CourseManagement_war_exploded">
                退出空间
            </a>
            <div class="content">
                <div id="personal-info">
                    <%String classid = (String) request.getAttribute("classid");%>
                    <%String newhwid = (String) request.getAttribute("newhwid");%>
                    <br>
                    <form action="randomGroup" method="post">
                        选择组数：
                    <select name="number">
                        <% for (int i = 2; i <= 15; i++) { %>
                        <option value="<%=i%>"><%=i%></option>
                        <% } %>
                    </select>
                        <input type="hidden" name="classid" value="<%=classid%>">
                        <input type="hidden" name="newhwid" value="<%=newhwid%>">
                        <input type="submit" value="实行分组" >
                    </form>
                    <br>
                    <h1 style="font-size: 17px">已有分组情况>></h1>
                    <%--                    <jsp:getProperty name="userBean" property="courseRes"/>--%>
                </div>
                <br>
                <%String backnews = "";
                    if(request.getAttribute("backnews")!=null){
                        backnews = (String) request.getAttribute("backnews");
                }%>
                <%=backnews%>
                <br>
                <%if(request.getAttribute("group")!=null){
                    ArrayList group=(ArrayList)request.getAttribute("group");}
                %>

                <table align="center" class="course-table">
                    <tr>
                        <th>小组ID</th>
                        <th>小组成员</th>
                        <%--                        <th>操作</th>--%>
                    </tr>
                    <%ArrayList groups = new ArrayList<>();
                        if(request.getAttribute("groups")!=null) {
                         groups = (ArrayList) request.getAttribute("groups");
                         }%>
                    <%for(int i=0;i<groups.size();i++){
                        Group group=(Group) groups.get(i);%>

                    <tr>
                        <%--                        <td><%=i+1 %></td>   <!-- 修改这里，使用 i+1 来表示第几行 -->--%>
                        <td><%=group.getGroupid() %></td>
                        <td><%=group.getStuname() %></td>
                        <%
                            }
                        %>
                    </tr>
                </table>

                <a href="/CourseManagement_war_exploded/submitGroup?classid=<%=classid%>&newhwid=<%=newhwid%>">
                    <div class="header">
                        <button class="logout-button">确认分组并返回</button>
                    </div>
                </a>
                <br>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
</html>