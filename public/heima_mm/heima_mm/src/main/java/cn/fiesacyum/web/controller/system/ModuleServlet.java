package cn.fiesacyum.web.controller.system;

import cn.fiesacyum.domain.system.Module;
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

@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {
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
        //ModuleService moduleService=new ModuleServiceImpl();
        int page=1;
        int size=5;
        if(StringUtils.isNotBlank(req.getParameter("page"))){
            page=Integer.parseInt(req.getParameter("page"));
        }
        if(StringUtils.isNotBlank(req.getParameter("size"))){
            size=Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = moduleService.findAll(page, size);
        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询得到含有所有部分的列表并传入到jsp的接收参数moduleList中
        List<Module> all = moduleService.findAll();
        req.setAttribute("moduleList",all);//使用请输入相较session节约内存
        req.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Module module = BeanUtil.fillBean(req, Module.class,"yyyy-MM-dd");
        moduleService.save(module);
        resp.sendRedirect(req.getContextPath()+"/system/module?operation=list");

    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询得到含有所有部分的列表并传入到jsp的接收参数moduleList中
        List<Module> all = moduleService.findAll();
        req.setAttribute("moduleList",all);//使用请输入相较session节约内存
        Module module = moduleService.findById(req.getParameter("id"));
        req.setAttribute("module",module);
        req.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(req,resp);

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Module module = BeanUtil.fillBean(req, Module.class,"yyyy-MM-dd");
        moduleService.update(module);
        resp.sendRedirect(req.getContextPath()+"/system/module?operation=list");

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Module module = BeanUtil.fillBean(req, Module.class,"yyyy-MM-dd");
        //ModuleService moduleService=new ModuleServiceImpl();
        moduleService.delete(module);
        resp.sendRedirect(req.getContextPath()+"/system/module?operation=list");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
