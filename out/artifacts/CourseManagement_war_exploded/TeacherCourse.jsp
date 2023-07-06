<%@ page import="java.util.ArrayList" %>
<%@ page import="unsolved.TCourse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择进入教学班级</title>
    <style>
        body {
            font-family: Arial, sans-serif;
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
        .sidebar a:hover {
            background-color: #f2f2f2;
        }
        .content {
            flex: 1;
            padding: 20px;
            background-color: white;
        }
        .label {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }
        .table-container th, .table-container td {
            padding: 10px;
            border: 1px solid cornflowerblue;
        }
        .form-container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
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
    </style>
</head>
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
                <a href="TeacherChooseCourse.jsp" class="a">课程活动>></a>
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
                <div id="password-form">
                    <a href="#" onclick="showTeacherSchedule()" class="a">我教的课>></a>
                    <%--                    <jsp:getProperty name="userBean" property="courseRes"/>--%>
                    <br>
                    <%
                        ArrayList courselist=(ArrayList)request.getAttribute("courselist");
                    %>**
                    <h2 align="center">课程列表</h2>
                    <table align="center">
                        <tr>
                            <th>课程名称</th>
                            <th>班级ID</th>
                            <th>上课时间</th>
                        </tr>
                            <%for(int i=0;i<courselist.size();i++){
                            TCourse tcourse=(TCourse) courselist.get(i);%>
                        <tr><th><%=tcourse.getCourseName() %></th>
                            <th><%=tcourse.getClasseId() %></th>
                            <th><%=tcourse.getCourseTime() %></th>
                                <%
                        } %>

                            <br>
                            <br>
                            <div id="schedule"></div>
                </div>
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

    function showTeacherSchedule() {
        fetch('/api/teacher-schedule')
            .then(response => response.json())
            .then(data => {
                var scheduleData = data.schedule;
                var scheduleDiv = document.getElementById('schedule');

                // 清空已有课程表内容
                scheduleDiv.innerHTML = '';

                // 创建一个包含表格的容器
                var tableContainer = document.createElement('div');
                tableContainer.classList.add('schedule-table-container');

                // 创建表格并添加表头
                var table = document.createElement('table');
                table.classList.add('schedule-table');

                var thead = document.createElement('thead');
                var tr = document.createElement('tr');
                var daysOfWeek = ['周一', '周二', '周三', '周四', '周五'];
                for (var i = 0; i < daysOfWeek.length; i++) {
                    var th = document.createElement('th');
                    th.textContent = daysOfWeek[i];
                    tr.appendChild(th);
                }
                thead.appendChild(tr);
                table.appendChild(thead);

                // 添加课程表内容
                var tbody = document.createElement('tbody');
                for (var i = 0; i < scheduleData.length; i++) {
                    var tr = document.createElement('tr');
                    for (var j = 0; j < daysOfWeek.length; j++) {
                        var td = document.createElement('td');
                        var course = scheduleData[i][j];
                        td.textContent = course;

                        // 为课程添加点击事件
                        td.addEventListener('click', function() {
                            // 获取选中的课程
                            var selectedCourse = this.textContent;
                            // 根据选中的课程进行跳转
                            if (selectedCourse === '课程1') {
                                window.location.href = 'course1.html';
                            } else if (selectedCourse === '课程2') {
                                window.location.href = 'course2.html';
                            } else if (selectedCourse === '课程3') {
                                window.location.href = 'course3.html';
                            }
                            // 添加其他课程的跳转逻辑...
                        });

                        tr.appendChild(td);
                    }
                    tbody.appendChild(tr);
                }
                table.appendChild(tbody);

                // 将表格添加到容器中
                tableContainer.appendChild(table);
                scheduleDiv.appendChild(tableContainer);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

</script>
</body>
</html>