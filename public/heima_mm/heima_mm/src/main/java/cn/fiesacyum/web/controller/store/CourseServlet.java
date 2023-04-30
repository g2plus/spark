package cn.fiesacyum.web.controller.store;

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

//uri:/store/course?operation=list
@WebServlet("/store/course")
public class CourseServlet extends BaseServlet {
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
        PageInfo all = courseService.findAll(pageNum, pageSize);
        req.setAttribute("page",all);//请求转发共享数据，将数据填入到表格中，并进入安全目录
        req.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = BeanUtil.fillBean(req, Course.class, "yyyy-MM-dd");
        courseService.save(course);
        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");

    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Course course = courseService.findById(id);
        req.setAttribute("course",course);
        req.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = BeanUtil.fillBean(req, Course.class, "yyyy-MM-dd");
        courseService.update(course);
        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = BeanUtil.fillBean(req, Course.class, "yyyy-MM-dd");
        courseService.delete(course);
        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
