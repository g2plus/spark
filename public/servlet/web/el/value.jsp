<%@ page import="top.arhi.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/30
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        h1{
            background-color:black;
            font-size:16px;
            color:white;
            padding:10px;
            margin:10px;
            height:20px;
        }
    </style>
</head>
<body>
    <%
        User user1 = new User("zhangsan",23,new Date());
        User user2 = new User("lisi",24,new Date());
        List list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        request.setAttribute("list",list);
    %>
    <%--获取值，jsp默认支持el表达式--%>
    ${not empty list}<br/>
    <h1>list集合的size为${list.size()}</h1>

    <%
        for(int i=0;i<list.size();i++){
           out.write(list.get(i).toString()+"<br/>");
        }
    %>
</body>
</html>
