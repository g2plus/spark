package top.arhi.service;

import top.arhi.domain.Student;

import java.util.List;

public interface StudentService {
    boolean save(Student student);
    boolean delete(String id);
    boolean update(Student student);
    List<Student> findAll();
    Student findById(String id);
}
