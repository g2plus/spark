package top.arhi.service;

import top.arhi.entity.PageResult;
import top.arhi.pojo.Setmeal;

import java.util.List;


public interface SetmealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);
}
