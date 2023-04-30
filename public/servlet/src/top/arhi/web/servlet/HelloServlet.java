package top.arhi.web.servlet;


import javax.servlet.*;
import java.io.IOException;


public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("only executed once");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String remoteAddr = servletRequest.getRemoteAddr();
        System.out.println(remoteAddr);
        System.out.println("Hello Servlet!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("execute this when you shut down the server!");
    }
}
