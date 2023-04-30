package top.arhi.service;

import top.arhi.domain.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly=true)
public interface StudentService {
    @Transactional(readOnly = false)
    boolean save(Student student);
    @Transactional(readOnly = false)
    boolean delete(String id);
    @Transactional(readOnly = false)
    boolean update(Student student);
    List<Student> findAll();
    Student findById(String id);
}
