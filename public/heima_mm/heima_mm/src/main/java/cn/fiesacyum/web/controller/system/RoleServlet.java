package cn.fiesacyum.web.controller.system;

import cn.fiesacyum.domain.system.Role;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
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
        }else if("author".equals(operation)){
            author(req,resp);
        }else if("updateRoleModule".equals(operation)){
            updateRoleModule(req,resp);
        }else{
        }
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
        PageInfo all = roleService.findAll(page, size);
        req.setAttribute("page",all);
        //通过请求转发进入到WEB-INF安全目录，由服务提供页面。可分享数据
        req.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Role role = BeanUtil.fillBean(req, Role.class,"yyyy-MM-dd");
        roleService.save(role);
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        req.setAttribute("role",roleService.findById(id));
        req.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Role role = BeanUtil.fillBean(req, Role.class,"yyyy-MM-dd");
        roleService.update(role);
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Role role = BeanUtil.fillBean(req, Role.class,"yyyy-MM-dd");
        roleService.delete(role);
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    private void author(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //获取要授权的角色id
        String roleId = req.getParameter("id");
        //使用id查询对应的数据（角色id对应的模块信息）
        Role role = roleService.findById(roleId);
        req.setAttribute("role",role);//页面需要显示role的相关属性，如role.name
        //根据当前的角色id获取所有的模块数据，并加载关系数据
        //key:value
        List<Map> map = moduleService.findAuthorDataByRoleId(roleId);//以roleId作为条件查询module,得到角色管理的模块(多个)
        //map转成json数据
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        req.setAttribute("list",json);
        // TODO 数据未查询
        //跳转到树页面中
        req.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(req,resp);
    }

    private void updateRoleModule(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String roleId = req.getParameter("roleId");
        String moduleIds = req.getParameter("moduleIds");
        System.out.println(roleId);
        System.out.println(moduleIds);
        roleService.updateRoleModule(roleId,moduleIds);
        resp.sendRedirect(req.getContextPath()+"/system/role?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
