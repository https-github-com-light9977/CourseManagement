<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.THomework" %>
<%@ page import="bean.HwSubmitList" %>
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
    <%-- 课程列表显示的布局样式--%>
    .homework-table {
      margin: 0 auto;
      overflow-y: auto;
      border-collapse: collapse;
      width: 90%;
      text-align: center;
    }
    .homework-table th, .homework-table td {
      padding: 10px;
      text-align: center;
      border: 0.5px solid #ccc;
    }
    .homework-table {
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
      <%for(int i=0;i<classinfo.size();i++){%>
      <td><%=classinfo.get(i)%></td>
      <%}%>

      <div class="choiceheader">
        <a class="choice" href="/CourseManagement_war_exploded/homework?classid=<%=classinfo.get(0)%>">作业</a>
        <a class="choice" href="#">签到</a>
        <a class="choice" href="/CourseManagement_war_exploded/notice?classid=<%=classinfo.get(0)%>" onclick="showContent('choice3')">通知</a>
        <a class="choice" href="#" onclick="showContent('choice4')">学生管理</a>
        <a class="choice" href="#" onclick="showContent('choice5')">分组</a>
      </div>

      <div id="choice1" class="choicecontent">
        <h1>作业提交详情>></h1>
        <table align="center" class="homework-table">
          <tr>
            <th>学生ID</th>
            <th>学生姓名</th>
            <th>完成评价</th>
            <th>操作</th>
          </tr>
          <%
            ArrayList submitLists=(ArrayList)request.getAttribute("submitLists");
          %>
          <%if(submitLists.size()>0){
            for(int i=0;i<submitLists.size();i++){
            HwSubmitList submitList=(HwSubmitList) submitLists.get(i);%>
          <tr><td><%=submitList.getStudent_id() %></td>
            <td><%=submitList.getStudent_name() %></td>
            <%String grade;
              if(request.getAttribute("grade") == null){
                grade = submitList.getGrade();
            }else {grade = (String) request.getAttribute("grade");
              }
            %>
            <td><%=grade %></td>

            <td>
              <a href="/CourseManagement_war_exploded/hw_details?classid=<%=classinfo.get(0)%>&hwid=<%=submitList.getHomework_id()%>&stuid=<%=submitList.getStudent_id()%>">
                <botton type="submit" class="submit-button">进入批改</botton>
              </a>
            </td>
          </tr>
          <%}
          }%>
          </td>
        </table>
<%--        <%--%>
<%--        ArrayList homeworks=(ArrayList)request.getAttribute("homeworks");--%>
<%--      %>--%>
<%--        <%for(int i=0;i<homeworks.size();i++){--%>
<%--          THomework tHomework=(THomework) homeworks.get(i);%>--%>
<%--        <tr><td><%=tHomework.getHwid() %></td>--%>
<%--          <td><%=tHomework.getHw_requirement() %></td>--%>
<%--          <td><%=tHomework.getDeadline() %></td>--%>
<%--          <td><button class="logout-button">进入批改</button></td>--%>
<%--        </tr>--%>
<%--        <% } %>--%>
        </table>

      </div>

    </div>
  </div>
</div>
</body>
<%--<div id="choice2" class="choicecontent">--%>
<%--  <button class="logout-button" >发布新签到</button>--%>
<%--  <h1>已发布签到列表</h1>--%>
<%--  <table>--%>
<%--    <tr>--%>
<%--      <th>签到名称</th>--%>
<%--      <th>操作</th>--%>
<%--    </tr>--%>
<%--    <%for(int i=0;i<courselist.size();i++){--%>
<%--      TCourse tcourse=(TCourse) courselist.get(i);%>--%>
<%--    <tr><td><%=tcourse.getCourseName() %></td>--%>
<%--      <td><button class="logout-button">查看详情</button></td>--%>
<%--    </tr>--%>
<%--    <% } %>--%>
<%--  </table>--%>

<%--</div>--%>

<%--<div id="choice3" class="choicecontent">--%>
<%--  <button class="logout-button" >发布新通知</button>--%>
<%--  <h1>已发布通知列表</h1>--%>
<%--  <table>--%>
<%--    <tr>--%>
<%--      <th>通知名称</th>--%>
<%--      <th>操作</th>--%>
<%--    </tr>--%>
<%--    <%for(int i=0;i<courselist.size();i++){--%>
<%--      TCourse tcourse=(TCourse) courselist.get(i);%>--%>
<%--    <tr><td><%=tcourse.getCourseName() %></td>--%>
<%--      <td><button class="logout-button">查看详情</button></td>--%>
<%--    </tr>--%>
<%--    <% } %>--%>
<%--  </table>--%>

<%--</div>--%>
<%--<div id="choice4" class="choicecontent">--%>
<%--  <h2>内容4</h2>--%>
<%--  <p>这是学生管理对应的内容。</p>--%>
<%--</div>--%>
<%--<div id="choice5" class="choicecontent">--%>
<%--  <h2>内容5</h2>--%>
<%--  <p>这是分组对应的内容。</p>--%>
<%--</div>--%>
<%--<script>--%>
<%--  function showContent(id) {--%>
<%--    var choices = document.getElementsByClassName('choice');--%>
<%--    for (var i = 0; i < choices.length; i++) {--%>
<%--      choices[i].classList.remove('active');--%>
<%--    }--%>
<%--    var contents = document.getElementsByClassName('choicecontent');--%>
<%--    for (var i = 0; i < contents.length; i++) {--%>
<%--      contents[i].style.display = 'none';--%>
<%--    }--%>

<%--    var choice = document.getElementById(id);--%>
<%--    var content = document.getElementById(id);--%>
<%--    choice.classList.add('active');--%>
<%--    content.style.display = 'block';--%>

<%--    var choiceWidth = choice.offsetWidth;--%>
<%--    var underlineWidth = choiceWidth - 20; // 调整下划线宽度，可以根据实际情况调整数值--%>
<%--    choice.style.setProperty('--underline-width', underlineWidth + 'px');--%>
<%--  }--%>
<%--</script>--%>

</html>
