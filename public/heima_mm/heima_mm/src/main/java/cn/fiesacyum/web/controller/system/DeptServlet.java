package cn.fiesacyum.web.controller.system;


import cn.fiesacyum.domain.system.Dept;
import cn.fiesacyum.service.system.DeptService;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//uri:/system/dept?operation=list
@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
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
        //DeptService deptService=new DeptServiceImpl();
        int page=1;
        int size=5;
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page=Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("size"))){
            size=Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = deptService.findAll(page, size);
        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询得到含有所有部分的列表并传入到jsp的接收参数deptList中
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList",all);//使用请输入相较session节约内存
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Dept dept = BeanUtil.fillBean(req, Dept.class,"yyyy-MM-dd");
        deptService.save(dept);
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");

    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询得到含有所有部分的列表并传入到jsp的接收参数deptList中
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList",all);//使用请输入相较session节约内存
        Dept dept = deptService.findById(req.getParameter("id"));
        req.setAttribute("dept",dept);
        req.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(req,resp);

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Dept dept = BeanUtil.fillBean(req, Dept.class,"yyyy-MM-dd");
        deptService.update(dept);
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Dept dept = BeanUtil.fillBean(req, Dept.class,"yyyy-MM-dd");
        //DeptService deptService=new DeptServiceImpl();
        deptService.delete(dept);
        resp.sendRedirect(req.getContextPath()+"/system/dept?operation=list");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}