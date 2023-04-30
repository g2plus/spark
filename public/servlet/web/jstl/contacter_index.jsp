<%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/31
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Bilibili,哔哩哔哩,哔哩哔哩动画,哔哩哔哩弹幕网,弹幕视频,B站,弹幕,字幕,AMV,MAD,MTV,ANIME,动漫,动漫音乐,游戏,游戏解说,二次元,游戏视频,ACG,galgame,动画,番组,新番,初音,洛天依,vocaloid,日本动漫,国产动漫,手机游戏,网络游戏,电子竞技,ACG燃曲,ACG神曲,追新番,新番动漫,新番吐槽,巡音,镜音双子,千本樱,初音MIKU,舞蹈MMD,MIKUMIKUDANCE,洛天依原创曲,洛天依翻唱曲,洛天依投食歌,洛天依MMD,vocaloid家族,OST,BGM,动漫歌曲,日本动漫音乐,宫崎骏动漫音乐,动漫音乐推荐,燃系mad,治愈系mad,MAD MOVIE,MAD高燃">
    <link rel="stylesheet" href="http://localhost${pageContext.servletContext.contextPath}/css/contacter_index.css">
    <link rel="shortcut icon" href="https://www.bilibili.com/favicon.ico?v=1">
</head>
<body>
    <table>
        <tr id="title">
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>省别</td>
            <td>QQ</td>
            <td>邮箱</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${requestScope.list}" var="contacter" varStatus="s">
            <tr>
                <td>${contacter.id}</td>
                <td>${contacter.name}</td>
                <td>${contacter.gender}</td>
                <td>${contacter.age}</td>
                <td>${contacter.province}</td>
                <td>${contacter.qq}</td>
                <td>${contacter.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/modifyContacterServlet?id=${contacter.id}">修改</a>
                    <a href="${pageContext.request.contextPath}/deleteContacterServlet?id=${contacter.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="7">
                <a href="http://localhost${pageContext.request.contextPath}/jstl/contacter_edit.jsp">添加联系人</a>
            </td>
        </tr>
    </table>
</body>
</html>
