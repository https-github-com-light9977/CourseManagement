<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.THomework" %>
<%@ page import="servlet.student.bean.Homework" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>作业</title>
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
            color: royalblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
        }
        .content {
            flex: 1;
            padding: 20px;
            background-color: white;
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
<body>
<jsp:useBean id="userBean" class="bean.User" scope="session"/>
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
            <%
                List classinfo=(List)request.getAttribute("classinfo");
            %>
            <%for(int i=0;i<classinfo.size();i++){%>
            <td><%=classinfo.get(i)%></td>
            <%}%>
        </div>
        <div>
            <a class="choiceheader" href="/CourseManagement_war_exploded/shomework?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">作业</a>
            <a class="choiceheader" href="/CourseManagement_war_exploded/scheckin?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">签到</a>
            <a class="choiceheader" href="/CourseManagement_war_exploded/snotice?classid=<%=classinfo.get(0)%>&stuid=<%=userBean.getLogid()%>">通知</a>
            <a class="choiceheader" href="/CourseManagement_war_exploded/sgrade?classid=<%=classinfo.get(0)%>&stuid<%=userBean.getLogid()%>">查看成绩</a>
        </div>
<br>
        <div class="choicecontent"></div>
        <div class="content" >
            <div class="logout-button" >作业内容详情>></div>
                    <form action="hwsubmit" method="post">
                        <%ArrayList<String> hwContent = (ArrayList)request.getAttribute("hwContent");
                            String content = hwContent.get(1);%>
                        <label for="content">作业要求:<%=content%></label>
                        <br>
                        <label for="content">我的答案:</label>
                        <textarea id="content" name="text" rows="5" cols="40"></textarea>
                        <br>
                        <label for="content">提交状态：<%=hwContent.get(2)%></label>
                        <br>
                        <label for="content">成绩：<%=hwContent.get(3)%></label>
                        <form method="post" action="upload.jsp" enctype="multipart/form-data">
                            <br/><br/>
                            <input type="file" name="file" /><br/><br/>
                            <input type="submit" value="上传已选文件" />
                        </form>
                        <input type="hidden" name="hwid" value="<%=hwContent.get(0)%>">
                        <input type="hidden" name="stuid" value="<%=userBean.getLogid()%>">
                        <input type="hidden" name="classid" value="<%=classinfo.get(0)%>">
                        <input type="submit" id="submit-button" value="确认提交"></input>
                    </form>
                </div>
                <%
                ArrayList homeworks=(ArrayList)request.getAttribute("homeworks");
                %>

            </div>
        </div>
</body>
</html>
