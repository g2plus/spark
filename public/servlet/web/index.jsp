<%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/27
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <style>
    button{
      width:100px;
      height:40px;
      background-color:orange;
      border-radius:20px;
      border:none;
    }
  </style>
  <body>
  <form action="${pageContext.request.contextPath}/forwardAndRedirect" method="post">
    <button type="submit">重定向测试</button>
  </form>
  </body>
</html>
