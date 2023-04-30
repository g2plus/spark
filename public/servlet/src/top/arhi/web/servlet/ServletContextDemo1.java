package top.arhi.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(loadOnStartup = -1,value="/servletContext")
public class ServletContextDemo1 extends HttpServlet {
    //TODO ServletContext对象的作用


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.ServletContext代表整个应用。获取方式有两种。
        ServletContext servletContext1 = req.getServletContext();
        ServletContext servletContext2= this.getServletContext();
        if (servletContext1==servletContext2) {
            System.out.println("true");
        }
        servletContext2.setAttribute("username","Google");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取应用数据比如公告。
        System.out.println(this.getServletContext().getAttribute("username"));
    }
}
