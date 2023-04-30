<%--
  Created by IntelliJ IDEA.
  User: arhi
  Date: 2021/9/2
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="/login" method="post">
    <label for="username">账号</label>
    <input type="text" id="username" name="username" value="" required/><br/>
    <label for="password">密&nbsp;码</label>
    <input type="password" id="password" name="password" value="" required/><br/>
    <button type="submit">登&nbsp;录</button>
</form>
</body>
</html>
