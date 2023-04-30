package top.arhi.web.servlet;

import com.alibaba.fastjson.JSON;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.arhi.domain.Account;
import top.arhi.util.DruidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/response")
public class Response extends HttpServlet {
    //TODO Response

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type","text/html;charset=utf-8");
        //resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write("{msg:\"Hello!\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //https://blog.csdn.net/sinat_40770656/article/details/103301886
        //fastjson的使用参考连接
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        try {
            String sql="select * from account where username=? and password=?";
            Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), username, password);
            String s = JSON.toJSONString(account);
            resp.getWriter().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
