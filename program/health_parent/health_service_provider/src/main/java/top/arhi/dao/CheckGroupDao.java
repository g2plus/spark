package top.arhi.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.arhi.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    
    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    void add(CheckGroup checkGroup);

    Page<CheckGroup> selectByCondition(@Param("value") String value);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);

    List<CheckGroup> findAll();

    void deleteById(Integer id);

    List<CheckGroup> findCheckGroupById(Integer id);
}
