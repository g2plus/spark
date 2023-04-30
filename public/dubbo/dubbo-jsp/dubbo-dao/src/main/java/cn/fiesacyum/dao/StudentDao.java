package cn.fiesacyum.dao;

import cn.fiesacyum.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentDao {

    @Insert("insert into student (id, name, age) values(#{id},#{name},#{age})")
    int save(Student student);

    @Delete("delete from student where id=#{id}")
    int delete(String id);

    @Update("update student set name=#{name},age=#{age} where id=#{id}")
    int update(Student student);

    @Select("select * from student")
    List<Student> findAll();

    @Select("select * from student where id=#{id}")
    Student findById(String id);
}
