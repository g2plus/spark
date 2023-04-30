package top.arhi.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import top.arhi.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    void add(Setmeal setmeal);
    void setSetmealAndCheckGroup(Map<String, Integer> map);
    Page<Setmeal> selectByCondition(@Param("value") String value);
    List<Setmeal> findAll();
    Setmeal findById(int id);
}
