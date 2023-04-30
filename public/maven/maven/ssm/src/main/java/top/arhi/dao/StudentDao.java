package top.arhi.dao;

import org.springframework.stereotype.Repository;
import top.arhi.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
