package top.arhi.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sessionServletDemo1")
public class SessionServletDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        //session依赖域cookie设置cookie的有效时长，当客户端关闭，
        // 然后在cookie有效期访问，服务器仍能找到之前的session
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
        session.setAttribute("msg","Hello world!");
        System.out.println(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
