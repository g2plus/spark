package cn.fiesacyum.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class AuthorFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request ;
        HttpServletResponse response;
        HttpSession session;
        try{
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
            session = request.getSession();

            //1.获取本次操作
            String url = request.getRequestURI();
            //.css   .js    .png   .jpg   .index
            if(url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith("index.jsp")
                    || url.endsWith("unauthorized.jsp")
                    || url.endsWith("login.jsp")){
                chain.doFilter(request,response);
                return;
            }
            String queryString = request.getQueryString();
            if(queryString.endsWith("operation=login")
                    ||queryString.endsWith("operation=home")
                    ||queryString.endsWith("operation=logout")
                    ||queryString.endsWith("operation=show")){
                chain.doFilter(request,response);
                return;
            }
            //1.当前获取到的url：   /system/dept
            url = url.substring(1);
            //2.当前获取到的查询参数：operation=list       operation=toEdit&id=100
            int index = queryString.indexOf('&');
            if(index != -1){
                queryString = queryString.substring(0,index);
            }
            url = url + "?" + queryString;

            //2.获取到当前登录人允许的操作
            String authorStr = session.getAttribute("authorStr").toString();

            //3.比对本次操作是否在当前登录人允许的操作范围内
            if(authorStr.contains(url)){
                //3.1如果允许，放行
                chain.doFilter(request,response);
                return;
            }else{
                //3.2不允许跳转到非法访问页
                response.sendRedirect(request.getContextPath()+"/unauthorized.jsp");
        }
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}
