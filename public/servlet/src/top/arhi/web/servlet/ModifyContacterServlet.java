package top.arhi.web.servlet;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.arhi.domain.Contacter;
import top.arhi.util.DruidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyContacterServlet")
public class ModifyContacterServlet extends HttpServlet {
    //TODO 完成数据的修改

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        //获取数据转发到页面
        String sql="select id,name,gender,age,province,qq,email from contacter where id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        Contacter contacter = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Contacter.class), id);
        req.setAttribute("id",id);
        req.setAttribute("name",contacter.getName());
        req.setAttribute("gender",contacter.getGender());
        req.setAttribute("age",contacter.getAge());
        req.setAttribute("province",contacter.getProvince());
        req.setAttribute("qq",contacter.getQq());
        req.setAttribute("email",contacter.getEmail());
        req.getRequestDispatcher(req.getContextPath()+"/jstl/contacter_edit.jsp").forward(req,resp);
    }
}
