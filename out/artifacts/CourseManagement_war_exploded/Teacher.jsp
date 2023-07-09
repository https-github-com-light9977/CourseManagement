<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>个人信息！</title>
  <style>
    body {
      font-family: sans-serif;
      height: 100%;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }
    <%-- .container是设置左右两块区域的布局样式 --%>
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
    <%--  设置右边显示区域“退出空间”按钮的布局样式--%>
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
    <%-- horizontal-menu是设置左边侧边栏的两个选项条的布局样式 --%>
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
    <%-- 右边显示个人信息和修改密码的布局样式 --%>
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
    <%-- 修改密码表单的布局样式 --%>
    .form-container {
      flex: 1;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
    }
    <%--修改密码标签的布局样式--%>
    .form-container label {
      margin-bottom: 5px;
    }
    <%--修改密码输入框的布局样式--%>
    .form-container input {
      padding: 5px;
      margin-bottom: 10px;
      width: 200px;
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
      font-size: 16px;
      font-weight: bold;
      color: black; /* 设置超链接的文本颜色为蓝色 */
      text-decoration: none; /* 去除超链接的下划线 */
    }
    a:hover {
      color: lightskyblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
    }
  </style>
</head>
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
      <div class="content">
        <!-- 个人信息界面 -->
        <div id="personal-info">
          <a label>个人信息>></a>
          <div id="personal-info-content" >
            <br><br><br><br>
            <p id="user-id">账号：<jsp:getProperty name="userBean" property="logid"/>
              <%--              <jsp:setProperty name="userBean" property="logid"/>--%>
            </p>
            <p id="userpassword">姓名：<jsp:getProperty name="userBean" property="name"/>
              <%--              <jsp:setProperty name="userBean" property="name"/>--%>
            </p>
          </div>
        </div>
        <br><br>
        <div id="password-form">
          <form action="resetPassword" method="post">
            <a label  >修改密码>></a>
            <br><br>
            <div id="password-form-content" >
              <div class="form-container">
                <label for="old-password">旧密码:</label><input type="password" id="old-password" name="old_password">
                <br>
                <label for="new-password">新密码:</label><input type="password" id="new-password" name="new_password">
                <br>
                <label for="confirm-password">确认新密码:</label><input type="password" id="confirm-password" name="confirm_password">
                <br>
                <input type="submit" id="submit-button" class="logout-button" value="提交">
                  <%
                String backnews = (String) request.getAttribute("back");
                if (backnews != null){
                  System.out.println(backnews);
                  out.println(backnews);
                }
              %>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>
</body>
</html>