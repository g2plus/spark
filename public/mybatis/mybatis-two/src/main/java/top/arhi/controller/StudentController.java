package top.arhi.controller;

import top.arhi.bean.Student;
import top.arhi.service.StudentService;
import top.arhi.service.impl.StudentServiceImpl;
import org.junit.Test;

import java.util.List;

/*
    控制层测试类
 */
public class StudentController {
    //创建业务层对象
    private StudentService service = new StudentServiceImpl();

    //查询全部功能测试
    @Test
    public void selectAll() {
        List<Student> students = service.selectAll();
        for (Student stu : students) {
            System.out.println(stu);
        }
    }
    //优点:条件的充分利用（条件的利用率为100%），在对应对象的Mapper接口添加注解@Param(String value)
    //注意点：mapper的配置文件xml只允许有一个parameterType属性
    @Test
    public void selectByNameOrAge(){
        List<Student> students = service.selectByNameOrAge("张三",24);
        for (Student stu : students) {
            System.out.println(stu);
        }
    }

    //缺点：需要对所有数据进行封装，条件使用不充分
    @Test
    public void selectByAgeOrName(){
        List<Student> students = service.selectByAgeOrName(new Student("1","张三",24));
        for (Student stu : students) {
            System.out.println(stu);
        }
    }

    //根据id查询功能测试
    @Test
    public void selectById() {
        Student stu = service.selectById("3");
        System.out.println(stu);
    }

    //新增功能测试
    @Test
    public void insert() {
        Student stu = new Student("4","张三",24);
        Integer result = service.insert(stu);
        System.out.println(result);
    }

    //修改功能测试
    @Test
    public void update() {
        Student stu = new Student("4","赵六",16);
        Integer result = service.update(stu);
        System.out.println(result);
    }

    //删除功能测试
    @Test
    public void delete() {
        Integer result = service.delete("4");
        System.out.println(result);
    }


}
