<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        form{
            text-align:center;
            margin:auto;
        }
        table{
            margin-top:200px;
            text-align:center;
            margin:auto;
        }
    </style>
</head>
<body>
<form action="/saveOrUpdate" method="post">
    <input type="hidden" name="id" value="${student.id}">
    姓名:<input type="text" name="name" value="${student.name}" required>
    年龄:<input type="text" name="age" value="${student.age}" required>
    <input type="submit" value="提交">
</form>
<div style="height:25px"></div>
<table border="1" cellpadding="0" cellspacing="0" width="800px">
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${students}" var="stu">
        <tr>
            <td>${stu.id}</td>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>
                <a href="/deleteStudent/${stu.id}" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/toUpdate/${stu.id}" >修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
<audio src="../audio/平行世界-G_E_M_邓紫棋-165923410.flac" controls="" autoplay="" loop=""></audio>
</body>
</html>
