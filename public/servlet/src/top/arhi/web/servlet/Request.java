package top.arhi.web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Parameter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(loadOnStartup = 0, value = "/request")
public class Request extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Map<String, String[]> map = (Map<String, String[]>)req.getAttribute("map");
        String s = JSON.toJSONString(map);
        if(null!=s){
            String realPath = this.getServletContext().getRealPath("/html/test.html");
            //解决reader乱码，采用字节流读取，设置编码集。
            InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(realPath)), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line="";
            while((line=br.readLine())!=null){
                resp.getWriter().write(line);
            }
            br.close();
            isr.close();
        }else{
            resp.getWriter().write(s);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        System.out.println(req.getMethod());
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String s = parameterNames.nextElement();
            System.out.println(s);
            System.out.println(req.getParameter(s));
        }
        resp.getWriter().write("<a href=\"https://www.baidu.com\">百度</a>");

    }
}
