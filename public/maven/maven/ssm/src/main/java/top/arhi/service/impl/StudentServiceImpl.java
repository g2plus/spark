package top.arhi.service.impl;

import top.arhi.dao.StudentDao;
import top.arhi.domain.Student;
import top.arhi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public boolean save(Student student) {
        student.setId(UUID.randomUUID().toString().replace("-",""));
        studentDao.save(student);
        return true;
    }

    @Override
    public boolean delete(String id) {
        studentDao.delete(id);
        return true;
    }

    @Override
    public boolean update(Student student) {
        studentDao.update(student);
        return true;
    }

    @Override
    public List<Student> findAll() {
       return studentDao.findAll();
    }

    @Override
    public Student findById(String id) {
        return studentDao.findById(id);
    }
}
