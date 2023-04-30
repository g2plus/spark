package cn.fiesacyum.dao.system;

import cn.fiesacyum.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    int save(Role role);
    int delete(Role role);
    int update(Role role);
    Role findById(String id);
    List<Role> findAll();
    //roleId字段属于Role类的字段，需要使用使用注解@Param("")
    void deleteRoleModule(@Param("roleId") String roleId);
    void saveRoleModule(@Param("roleId") String roleId, @Param("moduleId") String moduleId);
    List<Role> findAllRoleByUserId(@Param("userId") String userId);
}
