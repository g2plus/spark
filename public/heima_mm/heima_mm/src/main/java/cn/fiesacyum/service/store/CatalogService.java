package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Catalog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CatalogService {
    /**
     *
     * @param catalog
     */
    void save(Catalog catalog);

    /**
     *
     * @param catalog
     */
    void delete(Catalog catalog);

    /**
     *
     * @param catalog
     */
    void update(Catalog catalog);

    /**
     *
     * @param id
     * @return
     */
    Catalog findById(String id);

    /**
     *
     * @return
     */
    List<Catalog> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);
}
