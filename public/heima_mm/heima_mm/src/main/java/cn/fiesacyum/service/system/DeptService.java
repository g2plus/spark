package cn.fiesacyum.service.system;

import cn.fiesacyum.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface DeptService {
    /**
     *
     * @param dept
     */
    void save(Dept dept);

    /**
     *
     * @param dept
     */
    void delete(Dept dept);

    /**
     *
     * @param dept
     */
    void update(Dept dept);

    /**
     *
     * @param id
     * @return
     */
    Dept findById(String id);

    /**
     *
     * @return
     */
    List<Dept> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);
}