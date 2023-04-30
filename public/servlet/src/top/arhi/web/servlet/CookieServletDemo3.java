package top.arhi.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieServletDemo3")
public class CookieServletDemo3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取上次访问的时间
        boolean flag = true;
        Cookie[] cookies = req.getCookies();
        if (cookies == null || cookies.length == 0) {
            setCookie(resp);
        } else {
            //进行遍历，获取name为lastTime的cookie，如果不存在说明为第一次访问，
            for (Cookie cookie : cookies) {
                if ("lastTime".equals(cookie.getName())) {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("您上次的访问时间是" + URLDecoder.decode(cookie.getValue(), "UTF-8"));
                    //设置cookie新的时间值
                    setCookie(resp);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                //否则不是第一次访问。
                setCookie(resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private static void setCookie(HttpServletResponse resp) throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日 HH:mm:ss");
        String format = sdf.format(date);
        String encode = URLEncoder.encode(format, "UTF-8");
        Cookie cookie = new Cookie("lastTime", encode);
        resp.addCookie(cookie);
    }
}
