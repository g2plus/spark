<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div>
    <% Object username = session.getAttribute("username");%>
    <c:if test="${username eq null}">
        <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
    </c:if>
    <c:if test="${username ne null}">
        <a href="${pageContext.request.contextPath}/add.jsp.bak">添加学生信息</a>
        <a href="${pageContext.request.contextPath}/list">查询学生信息</a>
    </c:if>
</div>
<img src="img/timg.gif" width="50%" height="50%"/>
</body>
</html>
