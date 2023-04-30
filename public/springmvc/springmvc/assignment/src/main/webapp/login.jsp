<%--
  Created by IntelliJ IDEA.
  User: arhi
  Date: 2021/8/31
  Time: 0:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post" autocomplete="off"/>
用户名:<input type="text" name="name" value="" required/><br/>
密码:<input type="password" name="password" value="" required/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
