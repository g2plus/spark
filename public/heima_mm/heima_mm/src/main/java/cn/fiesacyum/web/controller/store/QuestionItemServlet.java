package cn.fiesacyum.web.controller.store;

import cn.fiesacyum.domain.store.QuestionItem;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if ("list".equals(operation)) {
            toList(req, resp);
        } else if ("save".equals(operation)) {
            save(req, resp);
        } else if ("toEdit".equals(operation)) {
            toEdit(req, resp);
        } else if ("edit".equals(operation)) {
            update(req, resp);
        } else if ("delete".equals(operation)) {
            delete(req, resp);
        } /*else if("saveOrUpdate".equals(operation)){
            saveOrUpdate(req,resp);
        }*/else{
        }
    }

  /*  private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) {
        使用前提：执行不能功能够通过一种媒介区分
        //将save与update功能合二为一。通过id判断执行不同的方法，需要将页面operation=${operation}改为operation=saveOrUpdate
        //toEdit方法去除setAttribute语句，toList方法中去除getAttribute(operation)setAttribute(operation,)
        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class);
        if(StringUtils.isNotBlank(questionItem.getId())){
            update(req,resp);
        }else{
            save(req,resp);
        }
    }*/
    private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req请求由question的list页面而来，请求设置目标页面的operation的属性为save
        // 请求发起端没有对operation进行设置属性对应的属性值，故响应页面进行获取属性值时为null。
        String questionId = req.getParameter("questionId");
        //设置目标页面的属性
        req.setAttribute("questionId", questionId);//写入到表单的隐藏域.下次提交获取，并在再次写入。
        PageInfo all = questionItemService.findAll(questionId, 1, 10);
        req.setAttribute("page", all);
        if(req.getAttribute("operation")==null){
            req.setAttribute("operation","save");//避免将edit属性值覆盖，导致无经进行修改。
        }
        //通过请求转发进入到WEB-INF安全目录（目标页面所在位置），由服务提供页面。可分享数据。
        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class);
        System.out.println(questionItem);
        questionItemService.update(questionItem);
        toList(req, resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId = req.getParameter("questionId");
        QuestionItem questionItem = BeanUtil.fillBean(req, QuestionItem.class);
        questionItemService.save(questionItem, questionId);
        toList(req, resp);

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String questionId = req.getParameter("questionId");
        QuestionItem questionItem = questionItemService.findById(id);
        questionItemService.delete(questionItem);
        /*//questItem1对象只有id的值不为null,其他成员的值均为null
        QuestionItem questionItem1 = BeanUtil.fillBean(req, QuestionItem.class);
        System.out.println(questionItem1);
        questionItemService.delete(questionItem);*/
        toList(req, resp);
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        //设置目标页面的某些属性
        QuestionItem questionItem = questionItemService.findById(id);
        req.setAttribute("questionItem", questionItem);
        req.setAttribute("operation","edit");//设置目标页面的operation属性值为edit
        toList(req, resp);//请求转发共享数据，将数据填入到表格中。
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
