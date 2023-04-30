package top.arhi.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


//1. Cookie 是浏览器访问服务器后，服务器传给浏览器的一段数据。
//2. 浏览器需要保存这段数据，不得轻易删除。
//3. 此后每次浏览器访问该服务器，都带上这段数据
//Cookie存储的信息是放到客户端的，用户在访问服务器端页面时，必然在客户端和服务器端之间频繁交换信息，影响了程序的性能。
//而Session由于存储在服务器内存中，因此不存在这个问题。不过，Session存储的信息是临时的，用户一旦关闭浏览器，状态即失去
//Application状态为应用程序提供了一个全局的状态。所有客户都可以使用该状态。从设计的角度来说，我们通常用Application来存储一些标准的数据。
//同时，我们在使用它时要注意避免性能的降低，存储的数据尽可能提供给客户只读的功能。
//https://blog.csdn.net/chen13333336677/article/details/100939030

@WebServlet("/cookieServletDemo1")
public class CookieServletDemo1 extends HttpServlet {
    //TODO 会话技术Cookie

    //TODO 获取上次请求时间cookie,lastTime(逻辑思维)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCookieLife(req,resp);
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cookie = new Cookie("msg","Hello");
        //如果cookie的name相同,后端只能接收后面一个cookie。
        Cookie cookie1 = new Cookie("msg","World!");
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
    }

    //cookie的原理
    private void cookiePrinciple(HttpServletResponse resp){
        String greet="Hello world!";
        String encode="";
        try {
            encode = URLEncoder.encode("msg=" + greet, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //数据以key=value的形式返回
        //resp.setHeader("Set-Cookie","msg="+greet);
        resp.setHeader("Set-Cookie",encode);
    }

    //设置cookie的生命周期
    private void setCookieLife(HttpServletRequest req, HttpServletResponse resp){
        Cookie cookie=new Cookie("code","302");
        cookie.setMaxAge(300);
        resp.addCookie(cookie);
    }
}
