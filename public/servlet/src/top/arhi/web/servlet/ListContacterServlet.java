package top.arhi.web.servlet;

import top.arhi.domain.Contacter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listContacterServlet")
public class ListContacterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List list = new ArrayList<Contacter>();
        //查询数据添加到请求领域
        req.setAttribute("list",list);
        req.getRequestDispatcher("http://localhost/jstl/contacter_index.jsp").forward(req,resp);
    }
}
