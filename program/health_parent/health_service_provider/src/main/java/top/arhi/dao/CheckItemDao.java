package top.arhi.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.arhi.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(@Param("value") String value);

    void deleteById(Integer id);

    long findCountByCheckItemId(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();

    List<CheckItem> findCheckItemById(Integer id);


}
