package cn.fiesacyum.web.controller.store;

import cn.fiesacyum.domain.store.Catalog;
import cn.fiesacyum.domain.store.Course;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//uri:/store/catalog?operation=list
@WebServlet("/store/catalog")
public class CatalogServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if("list".equals(operation)){
            toList(req,resp);
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
        int pageNum=1;
        int pageSize=5;
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            pageNum=Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("size"))){
            pageSize=Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = catalogService.findAll(pageNum, pageSize);
        req.setAttribute("page",all);
        //请求转发的目的，将信息填入到表格中,并进入到安全目录
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(req,resp);

    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courseList",courseService.findAll());
        //请求转发的目的，将信息填入到表格中
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class);
        catalogService.save(catalog);
        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Catalog catalog = catalogService.findById(req.getParameter("id")); //根据id参数来获取catalog
        Course course = catalog.getCourse();//通过catalog获取到课程信息
        List<Course> courseList=new ArrayList<>();//创建Course集合
        courseList.add(course);//将course存入集合供前端来提取数据.
        req.setAttribute("courseList",courseList);
        req.setAttribute("catalog",catalog);
        //请求转发的目的，将信息填入到表格中
        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class, "yyyy-MM-dd");
        catalogService.update(catalog);
        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Catalog catalog = BeanUtil.fillBean(req, Catalog.class, "yyyy-MM-dd");
        catalogService.delete(catalog);
        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
