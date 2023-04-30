package com.heima.freemarker.controller;

import com.heima.freemarker.pojos.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping("/freemarker")
    public String testFreemarker(Model model){

        model.addAttribute("name","world");
        Student student = new Student();
        student.setName("张三");
        student.setAge(23);
        model.addAttribute("stu",student);
        return "01-basic";
    }

    @GetMapping("/list")
    public String list(Model model){

        //------------------------------------
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setAge(19);

        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //向model中存放List集合数据
        model.addAttribute("stus",stus);

        //------------------------------------

        //创建Map数据
        HashMap<String,Student> stuMap = new HashMap<>(16);
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        // 3.1 向model中存放Map数据
        model.addAttribute("stuMap", stuMap);

        return "02-list";
    }

    @GetMapping("/baidu")
    public String baidu(){
        return "test";
    }

}
