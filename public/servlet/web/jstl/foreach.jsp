<%@ page import="top.arhi.domain.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>foreach</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Bilibili,哔哩哔哩,哔哩哔哩动画,哔哩哔哩弹幕网,弹幕视频,B站,弹幕,字幕,AMV,MAD,MTV,ANIME,动漫,动漫音乐,游戏,游戏解说,二次元,游戏视频,ACG,galgame,动画,番组,新番,初音,洛天依,vocaloid,日本动漫,国产动漫,手机游戏,网络游戏,电子竞技,ACG燃曲,ACG神曲,追新番,新番动漫,新番吐槽,巡音,镜音双子,千本樱,初音MIKU,舞蹈MMD,MIKUMIKUDANCE,洛天依原创曲,洛天依翻唱曲,洛天依投食歌,洛天依MMD,vocaloid家族,OST,BGM,动漫歌曲,日本动漫音乐,宫崎骏动漫音乐,动漫音乐推荐,燃系mad,治愈系mad,MAD MOVIE,MAD高燃">
    <link rel="shortcut icon" href="https://www.bilibili.com/favicon.ico?v=1">
    <link rel="stylesheet" href="http://localhost${pageContext.servletContext.contextPath}/css/foreach.css" type="text/css">
</head>
<body>
    <%
        User user1=new User("top1",23,new Date());
        User user2=new User("top2",24,new Date());
        User user3=new User("top3",25,new Date());
        User user4=new User("top4",23,new Date());
        User user5=new User("top5",24,new Date());
        User user6=new User("top6",25,new Date());
        List list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        list.add(user6);
        request.setAttribute("list",list);
    %>
    <table>
        <tr id="title">
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
            <td>时间</td>
        </tr>
        <c:forEach items="${requestScope.list}" var="user" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.time}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
