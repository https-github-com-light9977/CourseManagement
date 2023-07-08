<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.THomework" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>教师发布作业！</title>
  <!-- 引入日期选择器的CSS文件 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
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
    .choicecontent {
      padding: 20px;
      border-top: 2px solid #000;
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
        String classid = (String) request.getAttribute("classid");
      %>
      <%=classid%>
      <div class="choiceheader">
        <a class="choice" href="/CourseManagement_war_exploded/homework?classid=<%=classid%>">作业</a>
        <a class="choice" href="/CourseManagement_war_exploded/checkin?classid=<%=classid%>" >签到</a>
        <a class="choice" href="/CourseManagement_war_exploded/notice?classid=<%=classid%>">通知</a>

        <a class="choice" href="#" onclick="showContent('choice4')">学生管理</a>
        <a class="choice" href="#" onclick="showContent('choice5')">分组</a>
      </div>

      <div class="content" >
        <form action="submitreleaseHw" method="post">
          <label for="deadline">截止时间:</label>
          <input type="text" id="deadline" name="deadline" placeholder="点击选择截止时间">
          <br><br>
          <label for="publishTime">发布时间:</label>
          <input type="text" id="publishTime" name="publishTime" placeholder="点击选择发布时间">
          <br><br>
          <label for="content">发布内容:</label>
          <textarea id="content" name="content" rows="5" cols="40"></textarea>
          <br>
<%--          <form method="post" action="upload.jsp" enctype="multipart/form-data">--%>
<%--            <br/><br/>--%>
<%--            <input type="file" name="file" /><br/><br/>--%>
<%--            <input type="submit" value="上传已选文件" />--%>
<%--          </form>--%>
          <br>
          <label for="是否分组">是否分组:</label><br>
          <input type="radio" id="是" name="group" value="y">
          <label for="是">是</label>
          <input type="radio" id="student" name="group" value="n" checked>
          <label for="否">否</label><br><br>
          <input type="hidden" name="classid" value=<%=classid%>>
          <br>
          <input type="submit" id="submit-button" value="确认发布"></input>
        </form>


      </div>
    </div>

  </div>
</div>
</div>
</div>
<!-- 引入日期选择器的JS文件，并初始化日期选择器 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script>
  flatpickr("#deadline", {
    enableTime: true, // 允许选择具体时间
    dateFormat: "Y-m-d H:i", // 时间格式
    minDate: "today" // 只能选择今天及以后的日期和时间
  });

  flatpickr("#publishTime", {
    enableTime: true, // 允许选择具体时间
    dateFormat: "Y-m-d H:i", // 时间格式
    minDate: "today" // 只能选择今天及以后的日期和时间
  });
</script>
</body>

</html>