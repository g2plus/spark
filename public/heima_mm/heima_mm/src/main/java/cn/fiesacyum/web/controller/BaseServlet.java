package cn.fiesacyum.web.controller;

import cn.fiesacyum.service.store.*;
import cn.fiesacyum.service.store.impl.*;
import cn.fiesacyum.service.system.DeptService;
import cn.fiesacyum.service.system.ModuleService;
import cn.fiesacyum.service.system.RoleService;
import cn.fiesacyum.service.system.UserService;
import cn.fiesacyum.service.system.impl.DeptServiceImpl;
import cn.fiesacyum.service.system.impl.ModuleServiceImpl;
import cn.fiesacyum.service.system.impl.RoleServiceImpl;
import cn.fiesacyum.service.system.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected CompanyService companyService; //被保护的对象，仅有子类可用
    protected DeptService deptService;
    protected UserService userService;
    protected CourseService courseService;
    protected CatalogService catalogService;
    protected QuestionService questionService;
    protected QuestionItemService questionItemService;
    protected RoleService roleService;
    protected ModuleService moduleService;


    @Override
    public void init() throws ServletException {
        companyService=new CompanyServiceImpl();
        deptService=new DeptServiceImpl();
        userService=new UserServiceImpl();
        courseService=new CourseServiceImpl();
        catalogService=new CatalogServiceImpl();
        questionService=new QuestionServiceImpl();
        questionItemService=new QuestionItemServiceImpl();
        roleService=new RoleServiceImpl();
        moduleService=new ModuleServiceImpl();
    }
}
