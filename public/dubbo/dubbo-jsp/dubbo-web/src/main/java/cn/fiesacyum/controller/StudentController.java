package cn.fiesacyum.controller;

import cn.fiesacyum.pojo.Student;
import cn.fiesacyum.service.AccountService;
import cn.fiesacyum.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {

    //注入Service
    //@Autowired//本地注入

    /*
        1. 从zookeeper注册中心获取studentService的访问url
        2. 进行远程调用RPC
        3. 将结果封装为一个代理对象。给变量赋值

     */
    @Reference
    private StudentService studentService;

    @Reference
    private AccountService accountService;


    @RequestMapping("/addStudent")
    public String addStudent(Student student, HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        studentService.save(student);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        List<Student> all = studentService.findAll();
        model.addAttribute("students",all);
        return "list";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable String id,HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        studentService.delete(id);
        return "redirect:/list";
    }

    //使用Restful风格是，需要使用@Pathvariable来接收参数
    //请求路径：localhost:8000/student/findById/1.do
    //web.xml <url-pattern>*.do</url-pattern>
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable String id,Model model,HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        Student student = studentService.findById(id);
        model.addAttribute("student",student);
        return "forward:/list";
    }

    @RequestMapping("/updateStudent")
    public String updateStudent(Student student,HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        System.out.println(studentService.update(student));
        return "redirect:/list";
    }
    @RequestMapping("/login")
    public String login(String username,String password,HttpServletRequest req){
        boolean flag = accountService.find(username, password);
        if(flag){
            req.getSession().setAttribute("username",username);
            return "forward:/index.jsp";
        }
        return "forward:/login.jsp";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Student student){
        if(student.getId()!=null&&student.getId().length()!=0){
            studentService.update(student);
        }else{
            studentService.save(student);
        }
        return "redirect:/list";
    }
}

