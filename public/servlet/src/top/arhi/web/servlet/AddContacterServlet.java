package top.arhi.web.servlet;

import org.springframework.jdbc.core.JdbcTemplate;
import top.arhi.util.DruidUtil;
import top.arhi.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/addOrUpdateContacterServlet")
public class AddContacterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id==null){
            addContacter(req,resp);
        }else{
            updateContacter(Long.parseLong(id),req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    private void addContacter(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String province = req.getParameter("province");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");
        if(judge(name,gender,age,province,qq,email)){
            //进行数据保存
            String sql="insert into contacter (name,gender,age,province,qq,email) values(?,?,?,?,?,?)";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
            jdbcTemplate.update(sql,name,gender,age,province,qq,email);
            //重定向，jsp页面使用内置对象，进行遍历查询数据
            resp.sendRedirect("http://localhost"+req.getContextPath()+"/listContactServlet");
        }else{
            req.setAttribute("name",name);
            req.setAttribute("gender",gender);
            req.setAttribute("age",age.toString());
            req.setAttribute("province",province);
            req.setAttribute("qq",qq);
            req.setAttribute("email",email);
            req.getRequestDispatcher(req.getContextPath()+"/jstl/contacter_edit.jsp").forward(req,resp);
        }
    }

    private void updateContacter(Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        Integer age = Integer.parseInt(req.getParameter("age"));
        String province = req.getParameter("province");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");
        if(judge(name,gender,age,province,qq,email)){
            //进行数据更新
            String sql="update contacter set `name`=?,gender=?,age=?,province=?,qq=?,email=? where id=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.update(sql,name,gender,age,province,qq,email,id);
            resp.sendRedirect("http://localhost"+req.getContextPath()+"/listContactServlet");
        }else{
            req.setAttribute("id",id);
            req.setAttribute("name",name);
            req.setAttribute("gender",gender);
            req.setAttribute("age",age.toString());
            req.setAttribute("province",province);
            req.setAttribute("qq",qq);
            req.setAttribute("email",email);
            req.getRequestDispatcher(req.getContextPath()+"/jstl/contacter_edit.jsp").forward(req,resp);
        }
    }

    private static boolean judge(String name,String gender,Integer age,String province,String qq,String email) {
        if (StringUtils.isNotBlank(name)
                && StringUtils.isNotBlank(gender)
                && (age != null && (age > 0 && age < 140))
                && StringUtils.isNotBlank(province)
                && StringUtils.isNotBlank(qq)
                && checkEmail(email)
        ){
            return true;
        }
        return false;

    }

    private static boolean checkEmail(String email){
        final String regex="/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])/";
        if(StringUtils.isNotBlank(email)&&Pattern.matches(regex,email)){
            return true;
        }
        return false;

    }
}
