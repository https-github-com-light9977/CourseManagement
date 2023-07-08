<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.THomework" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.SCheckIn" %>
<%@ page import="bean.SHwGrade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>学生具体情况！</title>
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
      color: royalblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
    }
    .choiceheader {
      display: flex;
      flex-wrap: wrap;
      justify-content: flex-start;
      padding: 20px;
    }
    .choice {
      margin: 0 10px;
      text-decoration: none;
      color: #000;
      background-color: white;
      position: relative;
      display: inline-block;
      transition: all 0.3s ease;
    }
    .choice:after {
      content: "";
      position: absolute;
      bottom: -2px;
      left: 50%;
      transform: translateX(-50%);
      width: 80%;
      height: 4px;
      display: none;
    }
  </style>
</head>
<body>
<script>
  var courseTable = document.getElementById("course-table");
  var personalInfo = document.getElementById("personal-info");
  document.querySelector("a[href='#course-table']").addEventListener("click", function() {
    courseTable.style.display = "block";
    personalInfo.style.display = "none";
  });
  document.querySelector("a[href='#personal-info']").addEventListener("click", function() {
    courseTable.style.display = "none";
    personalInfo.style.display = "block";
  });
</script>
<jsp:useBean id="userBean" class="bean.Teacher" scope="session"/>
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
      <div class="choiceheader">
        <a class="choice" href="/CourseManagement_war_exploded/homework?classid=<%=classinfo.get(0)%>">作业</a>
        <a class="choice" href="/CourseManagement_war_exploded/checkin?classid=<%=classinfo.get(0)%>" >签到</a>
        <a class="choice" href="/CourseManagement_war_exploded/notice?classid=<%=classinfo.get(0)%>">通知</a>
        <a class="choice" href="/CourseManagement_war_exploded/manageStudent?classid=<%=classinfo.get(0)%>">学生管理</a>
        <a class="choice" href="#" onclick="showContent('choice5')">分组</a>
      </div>

      <%
        ArrayList<SCheckIn> sCheckIns=(ArrayList)request.getAttribute("sCheckIns");
      %>

      <%
        ArrayList<SHwGrade> sHwGrades=(ArrayList)request.getAttribute("sHwGrades");
      %>

      <div class="content" >
          <label for="content">学生ID：<%=sCheckIns.get(0).getStuid()%></label>
          <br>
          <label for="content">学生名字：<%=sCheckIns.get(0).getStuname()%></label>
          <br>
          <label for="content">签到情况：</label>
          <table align="center" class="course-table">
            <tr>
              <th>签到ID</th>
              <th>签到情况</th>
            </tr>

            <%for(int i=0;i<sCheckIns.size();i++){
              SCheckIn sCheckIn=(SCheckIn) sCheckIns.get(i);%>
            <tr>
              <td><%=sCheckIn.getCheckin_id() %></td>
              <td><%=sCheckIn.getChecked() %></td>
            </tr>
            <%}%>

          </table>
          <br>
          <label for="content">作业完成情况：</label>
          <table align="center" class="course-table">
            <tr>
              <th>作业ID</th>
              <th>成绩</th>
            </tr>
            <%for(int i=0;i<sHwGrades.size();i++){
              SHwGrade sHwGrade=(SHwGrade) sHwGrades.get(i);%>
            <tr>
              <td><%=sHwGrade.getHwid() %></td>
              <td><%=sHwGrade.getGrade() %></td>
            </tr>
            <%}%>

          </table>
          <br>
          <br>
          <br>
        <a href="/CourseManagement_war_exploded/manageStudent?classid=<%=classinfo.get(0)%>">
          <button id="submit-button">退出查看</button>
        </a>
      </div>
    </div>

  </div>
</div>
</div>
</div>
</body>
</html>
