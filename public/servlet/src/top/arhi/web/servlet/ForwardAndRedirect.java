package top.arhi.web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/forwardAndRedirect")
public class ForwardAndRedirect extends HttpServlet {
    //TODO forward与redirect的区别

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        Map map=new HashMap();
        map.put("username",req.getParameter("username"));
        map.put("age",req.getParameter("age"));
        map.put("id",req.getParameter("id"));
        map.put("phone",req.getParameter("phone"));
        String s = JSON.toJSONString(map);
        req.setAttribute("map",map);
        req.getRequestDispatcher("/request").forward(req,resp);
        //请求转发，下面的代码不在执行
        resp.getWriter().write(s+"......");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(302);
        int localPort = req.getLocalPort();
        //System.out.println(req.getRequestURI().toString());
        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath()+"/request");
        //resp.setHeader("location","http://localhost:"+localPort+req.getContextPath()+"/request");
    }
}
