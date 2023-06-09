package top.arhi.service;

import com.sun.istack.internal.NotNull;
import top.arhi.bean.Student;

import java.util.List;
/*
    业务层接口
 */
public interface StudentService {
    //查询全部
    public abstract List<Student> selectAll();

    //根据id查询
    public abstract Student selectById(@NotNull String id);

    //新增数据
    public abstract Integer insert(@NotNull Student stu);

    //修改数据
    public abstract Integer update(@NotNull Student stu);

    //删除数据
    public abstract Integer delete(@NotNull String id);
}
