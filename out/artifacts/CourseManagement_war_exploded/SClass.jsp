<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功进入课程班级！</title>
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
                <a href="/CourseManagement_war_exploded/scourse?stuid=<%=userBean.getLogid()%>" class="a">课程活动>></a>
                <a href="Student.jsp" class="a">个人信息>></a>
            </div>
        </div>
    </div>
    <div class="right">
        <div class="content-wrapper">
            <div class="header">
                <button class="logout-button">退出空间</button>
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
                <a id="choice1-link" class="choiceheader" href="/CourseManagement_war_exploded/shomework?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">作业</a>
                <a id="choice2-link" class="choiceheader" href="/CourseManagement_war_exploded/scheckin?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">签到</a>
                <a id="choice3-link" class="choiceheader" href="/CourseManagement_war_exploded/snotice?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">通知</a>
                <a id="choice4-link" class="choiceheader" href="/CourseManagement_war_exploded/sgrade?classid=<%=classinfo.get(0)%>&stuid<%=userBean.getLogid()%>">成绩</a>
            </div>
<script>
    function showContent(id) {
        var choices = document.getElementsByClassName('choice');
        for (var i = 0; i < choices.length; i++) {
            choices[i].classList.remove('active');
        }
        var contents = document.getElementsByClassName('choicecontent');
        for (var i = 0; i < contents.length; i++) {
            contents[i].style.display = 'none';
        }

        var choice = document.getElementById(id);
        var content = document.getElementById(id);
        choice.classList.add('active');
        content.style.display = 'block';

        var choiceWidth = choice.offsetWidth;
        var underlineWidth = choiceWidth - 20; // 调整下划线宽度，可以根据实际情况调整数值
        choice.style.setProperty('--underline-width', underlineWidth + 'px');
    }
</script>
    </div>
</div>
</div>
</body>
</html>