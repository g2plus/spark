package cn.fiesacyum.service.system;

import cn.fiesacyum.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    /**
     *
     * @param role
     */
    void save(Role role);

    /**
     *
     * @param role
     */
    void delete(Role role);

    /**
     *
     * @param role
     */
    void update(Role role);

    /**
     *
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     *
     * @return
     */
    List<Role> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);


    void updateRoleModule(String roleId, String moduleIds);


    List<Role> findAllRoleByUserId(String userId);
}
