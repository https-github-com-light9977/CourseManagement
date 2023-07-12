<%@ page import="java.util.ArrayList" %>
<%@ page import="user.teacher.bean.THomework" %>
<%@ page import="main.bean.Notice" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>通知</title>
  <!-- 引入Bootstrap样式 -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <!-- 自定义样式 -->
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
    .content {
      flex: 1;
      padding: 20px;
      background-color: white;
    }
    .notice-table {
      margin: 0 auto;
      overflow-y: auto;
      border-collapse: collapse;
      width: 90%;
      text-align: center;
    }
    .notice-table th, .notice-table td {
      padding: 10px;
      text-align: center;
      border-bottom: 0.5px solid #ccc;      <%-- 使列表里的竖直线不显示  --%>
    }
    .notice-table tr:nth-child(odd) {
      background-color: #f2f2f2;
    }
    .notice-table tr:hover {
      background-color: #e0e0e0;
    }                                      <%-- 以上两个样式是让列表里的奇数行颜色深浅与偶数行不一样，且鼠标悬浮到奇数行颜色会改变 --%>
    .notice-table th {
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
        <a class="logout-button" href="/CourseManagement_war_exploded">
          退出空间
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
      <a class="choiceheader" href="/CourseManagement_war_exploded/homework?classid=<%=classinfo.get(0)%>">作业</a>
      <a class="choiceheader" href="/CourseManagement_war_exploded/checkin?classid=<%=classinfo.get(0)%>" >签到</a>
      <a class="choiceheader" href="/CourseManagement_war_exploded/notice?classid=<%=classinfo.get(0)%>" onclick="showContent('choice3')">通知</a>
      <a class="choiceheader" href="/CourseManagement_war_exploded/manageStudent?classid=<%=classinfo.get(0)%>">学生管理</a>
<%--      <a class="choiceheader" href="#" onclick="showContent('choice5')">分组</a>--%>
    </div>
    <br>
    <div class="choicecontent"></div>
    <div class="content">
      <div><a class="logout-button" href="/CourseManagement_war_exploded/releaseNotice?classid=<%=classinfo.get(0)%>">
        发布新通知
      </a></div>
      <br><br><br><br>
      <div>
        <label class="logout-button">已发布通知列表>></label>
      </div>
      <br><br>
      <table align="center" class="notice-table">
        <tr>
          <th>序号</th>
<%--          <th>通知ID</th>--%>
          <th>通知详情</th>
          <th>通知发布时间</th>
          <th>操作</th>
        </tr>
        <%
          ArrayList<Notice> noticeArrayList=(ArrayList)request.getAttribute("noticeArrayList");
        %>
        <%if(noticeArrayList.size()>0){
          for(int i=0;i<noticeArrayList.size();i++){
            Notice notice=(Notice) noticeArrayList.get(i);%>
        <tr>
          <td><%=i+1 %></td>   <%-- 修改这里，使用 i+1 来表示第几行 --%>
<%--          <td><%=notice.getNoticeid() %></td>--%>
          <td><%=notice.getContent() %></td>
          <%String time;
            if(notice.getTime()==null){time = "";}else{time = notice.getTime();}%>
          <td><%=time %></td>
          <td><a class="logout-button" href="/CourseManagement_war_exploded/noticeDetail?classid=<%=classinfo.get(0)%>&no_id=<%=notice.getNoticeid()%>" >
            查看详情
          </a>
          </td>
        </tr>
        <% }
        }
        %>
      </table>
    </div>
  </div>
</div>
</div>
<!-- 引入Bootstrap脚本 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
