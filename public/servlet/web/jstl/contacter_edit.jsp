<%--
  Created by IntelliJ IDEA.
  User: e2607
  Date: 2022/1/31
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="Bilibili,哔哩哔哩,哔哩哔哩动画,哔哩哔哩弹幕网,弹幕视频,B站,弹幕,字幕,AMV,MAD,MTV,ANIME,动漫,动漫音乐,游戏,游戏解说,二次元,游戏视频,ACG,galgame,动画,番组,新番,初音,洛天依,vocaloid,日本动漫,国产动漫,手机游戏,网络游戏,电子竞技,ACG燃曲,ACG神曲,追新番,新番动漫,新番吐槽,巡音,镜音双子,千本樱,初音MIKU,舞蹈MMD,MIKUMIKUDANCE,洛天依原创曲,洛天依翻唱曲,洛天依投食歌,洛天依MMD,vocaloid家族,OST,BGM,动漫歌曲,日本动漫音乐,宫崎骏动漫音乐,动漫音乐推荐,燃系mad,治愈系mad,MAD MOVIE,MAD高燃">
    <link rel="stylesheet" href="http://localhost${pageContext.request.contextPath}/css/contacter_edit.css" >
    <script type="text/javascript" href="http://localhost${pageContext.request.contextPath}/js/verify.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}" method="post">
        <p><label for="nameLabel">姓名</label><input type="text" required="required"  name="name" id="nameLabel"></p>
        <p>
            <input type="radio" name="gender" value="男" required="required">
            <input type="radio" name="gender" value="女" required="required">
        </p>
        <p>
            <label for="ageLabel"></label><input type="number" name="age" required="required" id="ageLabel">
        </p>
        <p>
            <!--js鼠标停留进行查询-->
            <label for="provinceLabel">省份</label>
            <select name="province" id="provinceLabel">
                <c:forEach items="${provinceList}" var="item" varStatus="s">
                    <option value="${item.address}">${item.address}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label for="qqLabel">QQ</label>
            <input type="text" name="qq" required="required" id="qqLabel">
        </p>
        <p>
            <label for="emailLabel">Email</label>
            <input type="email" name="email" required="required" id="emailLabel">
        </p>
            <button type="submit" id="btn">提交</button>


    </form>
</body>

</html>
