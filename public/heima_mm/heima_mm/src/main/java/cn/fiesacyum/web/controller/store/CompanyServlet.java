package cn.fiesacyum.web.controller.store;

import cn.fiesacyum.domain.store.Company;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//uri:/store/company?operation=list
@WebServlet("/store/company")
public class CompanyServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if("list".equals(operation)){
            toList(req, resp);
        }else if("toAdd".equals(operation)){
            toAdd(req,resp);
        }else if("save".equals(operation)){
            save(req,resp);
        }else if("toEdit".equals(operation)){
            toEdit(req,resp);
        }else if("edit".equals(operation)){
            edit(req,resp);
        }else if("delete".equals(operation)){
            delete(req,resp);
        }else{}
    }

    private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page=1;
        int size=5;
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page=Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("size"))){
            size=Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = companyService.findAll(page, size);
        req.setAttribute("page",all);
        //通过请求转发进入到WEB-INF安全目录，由服务提供页面。可分享数据
        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Company company = BeanUtil.fillBean(req, Company.class,"yyyy-MM-dd");
        companyService.save(company);
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("company",companyService.findById(id));
        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Company company = BeanUtil.fillBean(req, Company.class,"yyyy-MM-dd");
        companyService.update(company);
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Company company = BeanUtil.fillBean(req, Company.class,"yyyy-MM-dd");
        companyService.delete(company);
        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
