<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功进入课程管理系统！</title>
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
            justify-content: center;
            padding: 10px;
            background-color: #f2f2f2;
            border-bottom: 1px solid #ccc;
        }

        .horizontal-menu a {
            margin: 0 10px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
        }

        .horizontal-menu a:hover {
            background-color: #f2f2f2;
        }

        .content {
            flex: 1;
            background-color: #fff;
            padding: 20px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }

        .label {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .list {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-gap: 10px;
        }

        .list-item {
            padding: 10px;
            border: 1px solid #ccc;
        }

        .table-container {
            overflow: auto;
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
<body>
<div class="container">
    <div class="sidebar">
        <div class="avatar"></div>
        <div class="profile-info">
            <h3 class="profile-name" id="profile-name">用户名</h3>
            <p class="profile-id" id="profile-id">用户ID</p>
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
            <!-- 作业 -->
            <div id="assignments">
                <!-- 作业内容 -->
            </div>
            <!-- 签到 -->
            <div id="check-in" style="display: none;">
                <!-- 签到内容 -->
            </div>
            <!-- 通知 -->
            <div id="notifications" style="display: none;">
                <!-- 通知内容 -->
            </div>
            <!-- 学生管理 -->
            <div id="student-management" style="display: none;">
                <!-- 学生管理内容 -->
            </div>
            <!-- 学生分组 -->
            <div id="student-groups" style="display: none;">
                <!-- 学生分组内容 -->
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
        });
    document.querySelector("a[href='#course-table']").addEventListener("click", function() {
// 显示课程信息区域
        document.getElementById("course-table").style.display = "block";
// 隐藏个人信息区域
        document.getElementById("personal-info").style.display = "none";
        var assignments = document.getElementById("assignments");
        var checkIn = document.getElementById("check-in");
        var notifications = document.getElementById("notifications");
        var studentManagement = document.getElementById("student-management");
        var studentGroups = document.getElementById("student-groups");
        document.querySelector("a[href='#assignments']").addEventListener("click", function() {
            assignments.style.display = "block";
            checkIn.style.display = "none";
            notifications.style.display = "none";
            studentManagement.style.display = "none";
            studentGroups.style.display = "none";
        });
        document.querySelector("a[href='#check-in']").addEventListener("click", function() {
            assignments.style.display = "none";
            checkIn.style.display = "block";
            notifications.style.display = "none";
            studentManagement.style.display = "none";
            studentGroups.style.display = "none";
        });
        document.querySelector("a[href='#notifications']").addEventListener("click", function() {
            assignments.style.display = "none";
            checkIn.style.display = "none";
            notifications.style.display = "block";
            studentManagement.style.display = "none";
            studentGroups.style.display = "none";
        });
        document.querySelector("a[href='#student-management']").addEventListener("click", function() {
            assignments.style.display = "none";
            checkIn.style.display = "none";
            notifications.style.display = "none";
            studentManagement.style.display = "block";
            studentGroups.style.display = "none";
        });
        document.querySelector("a[href='#student-groups']").addEventListener("click", function() {
            assignments.style.display = "none";
            checkIn.style.display = "none";
            notifications.style.display = "none";
            studentManagement.style.display = "none";
            studentGroups.style.display = "block";
        });
</script>
</body>
</html>
