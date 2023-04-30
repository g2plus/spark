package top.arhi.mapper;

import top.arhi.bean.Student;

import java.util.List;

public interface Many2ManyMapper {
    /**
     * 查询全部
     * @return
     */
    List<Student> selectAll();
}
