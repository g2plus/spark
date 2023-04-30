package cn.fiesacyum.service;

import cn.fiesacyum.pojo.Student;

import java.util.List;

public interface StudentService {
    String sayHello();
    boolean save(Student student);
    boolean delete(String id);
    boolean update(Student student);
    List<Student> findAll();
    Student findById(String id);

}
