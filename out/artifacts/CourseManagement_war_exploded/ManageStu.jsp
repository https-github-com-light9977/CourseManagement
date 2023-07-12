<%@ page import="java.util.ArrayList" %>
<%@ page import="user.teacher.bean.TCourse" %>
<%@ page import="user.teacher.bean.ManageStu" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级学生！</title>
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
        .logout-button:hover {
            color: lightskyblue; /* 当鼠标悬停在超链接上时，改变超链接的文本颜色为蓝色 */
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
        <%-- 右边显示区域的布局样式 --%>
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
        .student-table {
            margin: 0 auto;
            overflow-y: auto;
            border-collapse: collapse;
            width: 90%;
            text-align: center;
        }
        .student-table th, .student-table td {
            padding: 10px;
            text-align: center;
            border-bottom: 0.5px solid #ccc;      <%-- 使列表里的竖直线不显示  --%>
        }
        .student-table tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        .student-table tr:hover {
            background-color: #e0e0e0;
        }                                      <%-- 以上两个样式是让列表里的奇数行颜色深浅与偶数行不一样，且鼠标悬浮到奇数行颜色会改变 --%>
        .student-table th {
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
<%--            <a class="choiceheader" href="#" onclick="showContent('choice4')">分组</a>--%>
        </div>
        <br>
        <div class="choicecontent"></div>
        <div class="content">
            <div>
                <label class="logout-button">班级学生列表>></label>
                <%--                    <jsp:getProperty name="userBean" property="courseRes"/>--%>
            </div>

<%--            <label >设置成绩占比：</label>--%>
<%--            <form action="finalGrade" method="post">--%>
<%--                <br>签到：--%>
<%--                <br>--%>
<%--                <select name="checkin">--%>
<%--                    <% for (int i = 5; i <= 100; i=i+5) { %>--%>
<%--                    <option value="<%=i%>"><%=i%></option>--%>
<%--                    <% } %>%--%>
<%--                </select>--%>
<%--                <select name="hw">--%>
<%--                    <% for (int i = 5; i <= 100; i=i+5) { %>--%>
<%--                    <option value="<%=i%>"><%=i%></option>--%>
<%--                    <% } %>%--%>
<%--                </select>--%>
<%--                <input >--%>

            <br><br>
            <%
                ArrayList manageStus=(ArrayList)request.getAttribute("manageStus");
            %>
            <table align="center" class="student-table">
                <tr>
                    <th>序号</th>
                    <th>学生ID</th>
                    <th>学生名字</th>
                    <%--                        <th>签到情况</th>--%>
                    <th>签到分数</th>
                    <th>作业分数</th>
                    <th>操作</th>
                </tr>
                <%for(int i=0;i<manageStus.size();i++){
                    ManageStu manageStu=(ManageStu) manageStus.get(i);%>
                <tr>
                    <td><%=i+1 %></td>   <!-- 修改这里，使用 i+1 来表示第几行 -->
                    <td><%=manageStu.getStuid() %></td>
                    <td><%=manageStu.getStuname()%></td>
                    <%--                        <td><%=manageStu.getCheckin() %></td>--%>
                    <td><%=manageStu.getCheckinGrade() %></td>
                    <td><%=manageStu.getHwGrade() %></td>
                    <td><a class="logout-button" href="/CourseManagement_war_exploded/manageStuCon?classid=<%=classinfo.get(0)%>&stuid=<%=manageStu.getStuid()%>">
                        查看详情
                    </a></td>
                    <% } %>
                </tr>
            </table>
            <br>
            <button class="logout-button" onclick="exportToExcel()">导出学生成绩</button>

            <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
            <script>
                function exportToExcel() {
                    var wb = XLSX.utils.table_to_book(document.querySelector('.student-table'), { sheet: "学生成绩" });
                    var wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

                    function s2ab(s) {
                        var buf = new ArrayBuffer(s.length);
                        var view = new Uint8Array(buf);
                        for (var i = 0; i < s.length; i++) {
                            view[i] = s.charCodeAt(i) & 0xFF;
                        }
                        return buf;
                    }
                    function saveAs(blob, filename) {
                        var a = document.createElement('a');
                        a.href = URL.createObjectURL(blob);
                        a.download = filename;
                        a.style.display = 'none';
                        document.body.appendChild(a);
                        a.click();
                        document.body.removeChild(a);
                        URL.revokeObjectURL(a.href);
                    }

                    saveAs(new Blob([s2ab(wbout)], { type: "application/octet-stream" }), "student_scores.xlsx");
                }
            </script>
            <br>
        </div>
    </div>
</div>
</body>
</html>