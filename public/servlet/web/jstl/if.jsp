<%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>if</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        div{
            background-color:black;
            position:relative;
            left:50%;
            top:50%;
            transform:translate(-50%,-50%);
            -webkit-transform:translate(-50%,-50%);
        }
        div img{
            position:relative;
            width:400px;
            left:50%;
            top:0;
            transform: translate(-50%,0);
            -webkit-transform:translate(-50%,0);
        }
    </style>
</head>
<body>
<c:if test="${101>100}">
    <div>
        <img src="/upload/img/beauty.png" alt="" title="Long Live">
    </div>
</c:if>
</body>
</html>
