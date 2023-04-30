package top.arhi.web.servlet;

import org.springframework.jdbc.core.JdbcTemplate;
import top.arhi.util.DruidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteContacterServlet")
public class deleteContacterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //https://zhidao.baidu.com/question/1450358328478177580.html
        Long id = Long.parseLong(req.getParameter("id"));
        String sql="delete from contacter where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        //DQL语句使用jdbcTemplate的query相关方法,DML语句(数据表数据的增加，删除，修改)使用update方法。
        jdbcTemplate.update(sql);
        resp.sendRedirect("http://localhost"+req.getContextPath()+"/jstl/contacter_index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
