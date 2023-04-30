package cn.fiesacyum.service.impl;

import cn.fiesacyum.dao.StudentDao;
import cn.fiesacyum.pojo.Student;
import org.apache.dubbo.config.annotation.Service;
import cn.fiesacyum.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//@Service//将该类的对象创建出来，放到Spring的IOC容器中  bean定义

@Service//将这个类提供的方法（服务）对外发布。将访问的地址 ip，端口，路径注册到注册中心中
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    public String sayHello() {
        return "Hello Dubbo!";
    }

    public boolean save(Student student) {
        studentDao.save(student);
        return true;
    }

    public boolean delete(String id) {
        studentDao.delete(id);
        return true;
    }

    public boolean update(Student student) {
        studentDao.update(student);
        return true;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student findById(String id) {
        return studentDao.findById(id);
    }
}
