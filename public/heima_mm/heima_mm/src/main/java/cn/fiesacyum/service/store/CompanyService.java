package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Company;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CompanyService {
    /**
     *
     * @param company
     */
    void save(Company company);

    /**
     *
     * @param company
     */
    void delete(Company company);

    /**
     *
     * @param company
     */
    void update(Company company);

    /**
     *
     * @param id
     * @return
     */
    Company findById(String id);

    /**
     *
     * @return
     */
    List<Company> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);
}
