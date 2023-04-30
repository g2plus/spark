<%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        body{
            background-color: skyblue;
        }


    </style>
</head>
<body>
    <%
        request.setAttribute("day","Sunday");
    %>
    <%--获取值的方式${域名称.键名}}--%>
    <c:choose>
        <c:when test="${requestScope.day == \"Sunday\"}">
            <div style="background-color:blue">Sunday</div>
        </c:when>
        <c:when test="${requestScope.day == \"Monday\"}">
            <div style="background-color:orange">Monday</div>
        </c:when>
        <c:when test="${requestScope.day == \"Tuesday\"}">
            <div style="background-color:green">Tuesday</div>
        </c:when>
        <c:when test="${requestScope.day == \"Thursday\"}">
            <div style="background-color:brown">Thursday</div>
        </c:when>
        <c:when test="${requestScope.day == \"Friday\"}">
            <div style="background-color:grey">Friday</div>
        </c:when>
        <c:when test="${requestScope.day == \"Saturday\"}">
            <div style="background-color:white">Saturday</div>
        </c:when>
        <c:otherwise>invalid </c:otherwise>
    </c:choose>



</body>
</html>
