package com.itheima.service;

import com.itheima.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentService {
    boolean save(Student student);
    boolean delete(String id);
    boolean update(Student student);
    List<Student> findAll();
    Student findById(String id);
}
