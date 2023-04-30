package top.arhi.controller;

import top.arhi.domain.Student;
import top.arhi.service.AccountService;
import top.arhi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/addStudent")
    public String addStudent(Student student,HttpServletRequest req){
        Object username = req.getSession().getAttribute("username");
        if(username==null){
            return "redirect:/login.jsp";
        }
        studentService.save(student);
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model,HttpServletRequest req){
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
