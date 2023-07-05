<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>个人信息</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      height: 100%;
      margin: 0;
      padding: 0;
      background-color: #f5f5f5;
    }
    .container {
      display: flex;
      height: 100vh;
    }
    .sidebar {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      width: 200px;
      background-color: #f2f2f2;
      padding: 20px;
    }
    .sidebar .avatar {
      width: 100px;
      height: 100px;
      background-color: gray;
      border-radius: 50%;
      margin-bottom: 10px;
    }
    .sidebar .profile-info {
      text-align: center;
    }
    .sidebar .profile-name {
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 5px;
    }
    .sidebar .profile-id {
      font-size: 14px;
      color: #666;
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
      background-color: #f2f2f2;
      border-bottom: 1px solid #ccc;
    }
    .header-title {
      font-size: 24px;
      font-weight: bold;
    }
    .logout-button {
      padding: 10px 20px;
      background-color: #f44336;
      border: none;
      color: #fff;
      font-size: 16px;
      font-weight: bold;
      cursor: pointer;
    }
    .horizontal-menu {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 10px;
      background-color: #f2f2f2;
      border-bottom: 1px solid #ccc;
    }
    .horizontal-menu a {
      margin-bottom: 10px;
    }
    .content {
      flex: 1;
      background-color: #fff;
      padding: 20px;
    }
    .label {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 10px;
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px;
      border: 1px solid #ccc;
    }
  </style>
</head>
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
<body>
<jsp:useBean id="loginBean" class="bean.Login" scope="session"/>
<div class="container">
  <div class="sidebar">
    <div class="avatar"></div>
    <div class="profile-info">
      <h3 class="profile-name" id="profile-name">
        <jsp:getProperty name="loginBean" property="name"/>
      </h3>
      <p class="profile-id" id="profile-id">
        <jsp:getProperty name="loginBean" property="logid"/></p>
    </div>
    <div class="horizontal-menu">
      <a href="#course-table">课程活动</a>
      <a href="#personal-info">个人信息</a>
    </div>
  </div>
  <div class="content-wrapper">
    <div class="header">
      <div class="header-title">西南财经大学</div>
      <button class="logout-button">退出空间</button>
    </div>
    <div class="content">
      <!-- 个人信息界面 -->
      <div id="personal-info" style="">
        <div class="label">个人信息</div>
        <p id="user-id">ID：
          <jsp:getProperty name="loginBean" property="logid"/></p>
        <p id="userpassword">姓名：
          <jsp:getProperty name="loginBean" property="name"/></p>
        <div id="change-password-label">修改密码:</div><br>
        <label for="old-password">输入旧密码:</label><br>
        <input type="password" id="old-password" name="old-password"><br>
        <label for="new-password">输入新密码:</label><br>
        <input type="password" id="new-password" name="new-password"><br>
        <label for="confirm-password">确认新密码:</label><br>
        <input type="password" id="confirm-password" name="confirm-password"><br>
        <button id="submit-button">提交</button>
      </div>
    </div>
  </div>
</div>
<script>
  // 通过后端数据获取用户信息
  fetch('/api/user')
          .then(response => response.json())
          .then(data => {
            document.getElementById('profile-name').textContent = data.name;
            document.getElementById('profile-id').textContent = 'ID: ' + data.id;
            document.getElementById('user-id').textContent = '用户ID: ' + data.id;

            var changePasswordButton = document.getElementById('change-password-button');
            var changePasswordCheckbox = document.getElementById('change-password');
            changePasswordButton.addEventListener("click", function() {
              if (changePasswordCheckbox.checked) {
                alert("修改密码");
              } else {
                alert("密码未修改");
              }
            });
          });
</script>
</body>
</html>
