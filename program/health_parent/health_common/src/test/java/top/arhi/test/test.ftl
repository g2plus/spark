<html>
<head>
    <meta charset="utf-8">
    <title>Freemarker入门</title>
</head>
<body>
<#if success=true>
    你已通过实名认证
<#else>
    你未通过实名认证
</#if>
<#--我只是一个注释，我不会有任何输出  -->
${name}你好，${message}
<#list goodsList as goods>
    商品名称： ${goods.name} 价格：${goods.price}<br>
</#list>
</body>
</html>