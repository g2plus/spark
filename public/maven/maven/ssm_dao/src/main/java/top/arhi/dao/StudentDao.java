package top.arhi.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.arhi.domain.Student;

import java.util.List;

@Repository
public interface StudentDao {
    @Insert("insert into student (id, name, age) values(#{id},#{name},#{age})")
    void save(Student student);

    @Delete("delete from student where id=#{id}")
    void delete(String id);

    @Update("update student set name=#{name},age=#{age} where id=#{id}")
    void update(Student student);

    @Select("select * from student")
    List<Student> findAll();

    @Select("select * from student where id=#{id}")
    Student findById(String id);
}
