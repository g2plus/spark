package top.arhi.web.servlet;

import org.springframework.jdbc.core.*;
import top.arhi.domain.Account;
import top.arhi.util.DruidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(loadOnStartup = 0, value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        try{
            String sql = "select * from account where username=? and password=?";
            Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), username, password);
            if(account!=null) {
                //请求转发，为一次请求，请求方式相同。
                req.setAttribute("username", username);
                req.getRequestDispatcher("/successServlet").forward(req, resp);
            }
        }catch(Exception e){
            req.getRequestDispatcher("/failServlet").forward(req,resp);
            e.printStackTrace();
        }
    }
}
