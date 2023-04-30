package cn.fiesacyum.web.controller.store;

import cn.fiesacyum.domain.store.Question;
import cn.fiesacyum.utils.BeanUtil;
import cn.fiesacyum.utils.FileUtil;
import cn.fiesacyum.web.controller.BaseServlet;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        if("list".equals(operation)){
            toList(req, resp);
        }else if("toAdd".equals(operation)){
            toAdd(req,resp);
        }else if("save".equals(operation)){
            try {
                save(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("toEdit".equals(operation)){
            toEdit(req,resp);
        }else if("edit".equals(operation)){
            try {
                edit(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("delete".equals(operation)){
            delete(req,resp);
        }else if("toUpload".equals(operation)){
            toUpload(req,resp);
        }else if("testFileUpload".equals(operation)){
            try {
                testFileUpload(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("show".equals(operation)){
            show(req,resp);
        }else if("toExport".equals(operation)){
            toExport(req,resp);
        }else{}
    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath()+"/upload/1P424115555-10-lp.jpg");
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
        PageInfo all = questionService.findAll(page, size);
        req.setAttribute("page",all);
        //请求转发共享数据，将数据填入到表格中，并进入安全目录
        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companyList",companyService.findAll());
        req.setAttribute("catalogList",catalogService.findAll());
        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        /*Question question = BeanUtil.fillBean(req, Question.class,"yyyy-MM-dd");
        questionService.save(question);*/
        //request提交含有普通表单与文件表单，request被封装了需要使用FileItem做处理！！！
        //fillBean(List<FileItem> items,Class<T> clazz)
        if(ServletFileUpload.isMultipartContent(req)){
            DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            //System.out.println(fileItems);


            //加入标记为记录是否有文件上传，使用StringUtils.isNotBlank进行判断。
            boolean uploadedFlag=false;
            for (FileItem fileItem : fileItems) {
                if(fileItem.getName()!=null&&(!fileItem.getName().isEmpty())){
                    uploadedFlag=true;
                    break;
                }
            }
            //并没有对picture的值进行赋值操作，需要在业务层补上一个文件名的设置
            Question question = BeanUtil.fillBean(fileItems, Question.class);

            String fileNameInId = questionService.save(question,uploadedFlag);

            for (FileItem fileItem : fileItems) {
                if(!fileItem.isFormField()){
                    fileItem.write(new File(req.getServletContext().getRealPath("upload"),fileNameInId));
                }
            }

        }
        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companyList",companyService.findAll());
        req.setAttribute("catalogList",catalogService.findAll());

        //根据id获取到question对象（前端需要的是question对象），供前端进行数据的提取进行页面的显示
        //question.remark,question.state
        String id = req.getParameter("id");
        Question question = questionService.findById(id);
        req.setAttribute("question",question);//同一请求，数据共享。

        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
       /*Question question = BeanUtil.fillBean(req, Question.class,"yyyy-MM-dd");
        questionService.save(question);*/
        //request提交含有普通表单与文件表单，request被封装了需要使用FileItem做处理！！！
        //fillBean(List<FileItem> items,Class<T> clazz)
        if(ServletFileUpload.isMultipartContent(req)){
            DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);

            //并没有对picture的值进行赋值操作，需要在业务层补上一个文件名的设置
            Question question = BeanUtil.fillBean(fileItems, Question.class);

            //存在的问题，如果不进行上传文件，无法通过id来获取图片，页面显示异常。
            //解决方案，在保存时，价格标记为判断是否，上传文件，值update.jsp中加入添加判断，没有图片不显示div盒子

            boolean uploadedFlag=false;
            for (FileItem fileItem : fileItems) {
                if(fileItem.getName()!=null&&(!fileItem.getName().isEmpty())){
                    uploadedFlag=true;
                    break;
                }
            }

            questionService.update(question,uploadedFlag);

            for (FileItem fileItem : fileItems) {
                if(!fileItem.isFormField()){
                    //覆盖原有文件
                    fileItem.write(new File(req.getServletContext().getRealPath("upload"),question.getId()));
                }
            }

        }
        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");

    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //2021-8-20 13:00 缺陷无法删除文件对应的图片
        Question question = BeanUtil.fillBean(req, Question.class,"yyyy-MM-dd");
        //QuestionService questionService=new QuestionServiceImpl();
        String questionId = question.getId();
        System.out.println(questionId);
        questionItemService.deleteByQuestionId(questionId);
        questionService.delete(question);
        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");
    }

    /*private void toExamine(HttpServletRequest req, HttpServletResponse resp) {

    }*/

    private void toUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(req,resp);
    }

    private void testFileUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /**
         * 1.判断该文件是否支持文件上传操作 enctype="multipart/form-data"
         * 2.创建磁盘工厂类对象
         * 3.Servlet文件上传核心对象
         * 4.从request中读取数据
         * 5.判断当前表单是否为文件表单，如果写入文件*/
        //1.判断该文件是否支持文件上传操作 enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(req)){
            //2.创建磁盘工厂类对象,可以控制文件上传的大小，临时文件存放位置。
            //https://blog.csdn.net/Leon_Jinhai_Sun/article/details/105154212
            DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
            //3.Servlet文件上传核心对象
            ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
            //4.从request中读取数据
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            //5.判断当前表单是否为文件表单，如果不是(false)则写入文件
            for (FileItem fileItem : fileItems) {
                if(!fileItem.isFormField()){
                    //通过Servlet上下文路径找到upload的实际位置并与fileItem.getName()
                    //获取到的文件名及进行拼接来确认文件的存储位置。
                    //备注在实际开发中，已存在媒体服务器中。
                    fileItem.write(new File(req.getServletContext().getRealPath("upload"),fileItem.getName()));
                }
            }
        }
        resp.sendRedirect("/store/question?operation=list");
    }

    private void toExport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//使用fileInputStream与ServletOutputStream实现文件下载
        String target = req.getServletContext().getRealPath("/upload/test.flac");
        FileInputStream fis=new FileInputStream(target);
        String fileName=new String("test.flac".getBytes(),"iso8859-1");
        resp.setHeader("Content-Disposition","attachment;filename="+fileName);
        ServletOutputStream sos = resp.getOutputStream();
        int len=0;
        byte[] buf=new byte[1024*1024*20];
        while((len=fis.read(buf))!=-1){
            sos.write(buf,0,len);
        }
        fis.close();
        sos.close();*/


        //设置内容响应类型
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String fileName=new String("题目.xlsx".getBytes(),"iso8859-1");
        //设置响应头通知浏览器进行文件下载
        resp.setHeader("Content-Disposition","attachment;filename="+fileName);
        ByteArrayOutputStream os = questionService.getReport();
        ServletOutputStream sos = resp.getOutputStream();
        os.writeTo(sos);
        sos.flush();//输入刷新
        os.close();
        sos.close();
        toList(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
