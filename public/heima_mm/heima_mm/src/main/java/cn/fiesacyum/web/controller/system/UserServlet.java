package cn.fiesacyum.web.controller.system;


import cn.fiesacyum.domain.system.Dept;
import cn.fiesacyum.domain.system.Module;
import cn.fiesacyum.domain.system.Role;
import cn.fiesacyum.domain.system.User;
import cn.fiesacyum.service.system.RoleService;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.utils.MD5Util;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
///system/user?operation
@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            toList(req, resp);
        } else if ("toAdd".equals(operation)) {
            toAdd(req, resp);
        } else if ("save".equals(operation)) {
            save(req, resp);
        } else if ("toEdit".equals(operation)) {
            toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            edit(req, resp);
        } else if ("delete".equals(operation)) {
            delete(req, resp);
        } else if("userRoleList".equals(operation)){
            userRoleList(req,resp);
        } else if("updateRole".equals(operation)){
            updateRole(req,resp);
        } else if("login".equals(operation)){
            login(req,resp);
        } else if("logout".equals(operation)){
            logout(req,resp);
        } else if("home".equals(operation)){
            home(req,resp);
        }else {}
    }

    private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //UserService userService=new UserServiceImpl();
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(req.getParameter("page"))) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (StringUtils.isNotBlank(req.getParameter("size"))) {
            size = Integer.parseInt(req.getParameter("size"));
        }
        PageInfo all = userService.findAll(page, size);
        req.setAttribute("page", all);

        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/system/user/list.jsp").forward(req, resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList",all);
        req.getRequestDispatcher( "/WEB-INF/pages/system/user/add.jsp").forward(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        userService.save(user);
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dept> all = deptService.findAll();
        req.setAttribute("deptList",all);
        String id = req.getParameter("id");
        User user = userService.findById(id);
        req.setAttribute("user",user);
        req.getRequestDispatcher( "/WEB-INF/pages/system/user/update.jsp").forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = BeanUtil.fillBean(req, User.class,"yyyy-MM-dd");
        userService.update(user);
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = BeanUtil.fillBean(req, User.class, "yyyy-MM-dd");
        userService.delete(user);
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }

    //findAllRoleByUserId
    private void userRoleList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        User user = userService.findById(userId);
        req.setAttribute("user",user);
        List<Role> roleList = roleService.findAllRoleByUserId(userId);
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/system/user/role.jsp").forward(req, resp);
    }
    //updateRole
    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        String[] roleIds = req.getParameterValues("roleIds");
        if(roleIds!=null){
            for (String roleId : roleIds) {
                System.out.println(roleId);
            }
        }
        userService.updateRole(userId,roleIds);
        resp.sendRedirect(req.getContextPath() + "/system/user?operation=list");
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.login(email, password);
        //如果数据库中不存在该用户信息，跳转至登录界面；否则放行
        if(user!=null){
            req.getSession().setAttribute("loginUser",user);
            //如果登录成功，加载该用户对应的角色对应的所有模块
            List<Module> moduleList = userService.findModuleById(user.getId());

            req.setAttribute("moduleList",moduleList);
            //当前登录用户对应的可操作模块的所有url拼接成一个大的字符串，进行访问路径的控制（白名单）
            StringBuffer sbf = new StringBuffer();
            for(Module m: moduleList){
                sbf.append(m.getCurl());
                sbf.append(',');
            }
            req.getSession().setAttribute("authorStr",sbf.toString());
            //跳转页面
            req.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(req, resp);
        }else{
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取session域的内容，清空。回转至login.jsp
        req.getSession().setAttribute("loginUser",null);
        resp.sendRedirect(req.getContextPath()+"/login.jsp");
    }

    private void home(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}