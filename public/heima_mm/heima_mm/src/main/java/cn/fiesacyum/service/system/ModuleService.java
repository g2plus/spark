package cn.fiesacyum.service.system;

import cn.fiesacyum.domain.system.Module;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ModuleService {
    /**
     *
     * @param module
     */
    void save(Module module);

    /**
     *
     * @param module
     */
    void delete(Module module);

    /**
     *
     * @param module
     */
    void update(Module module);

    /**
     *
     * @param id
     * @return
     */
    Module findById(String id);

    /**
     *
     * @return
     */
    List<Module> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);

    List<Map> findAuthorDataByRoleId(String roleId);
}
