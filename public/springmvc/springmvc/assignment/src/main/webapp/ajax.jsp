<%@page pageEncoding="UTF-8" language="java" contentType="text/html;UTF-8" %>

<a href="javascript:void(0);" id="list">遍历列表</a><br/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
    $(function () {
        //为id="testAjaxReturnJsonList"的组件绑定点击事件
        $("#list").click(function(){
            //发送异步调用
            $.ajax({
               type:"POST",
               url:"/list",
               //回调函数
               success:function(data){
                    //alert(data);
                    //alert(data.length);
                    //alert(data[0]["name"]);
                    //alert(data[1]["age"]);
                   for(var j = 0; j < data.length; j++) {
                       document.write(data[j]["name"]+" "+data[j]["password"]+"<br/>")
                   }
               }
            });
        });
    });
</script>