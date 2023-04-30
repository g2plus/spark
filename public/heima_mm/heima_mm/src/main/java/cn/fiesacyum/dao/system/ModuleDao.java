package cn.fiesacyum.dao.system;

import cn.fiesacyum.domain.system.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ModuleDao {
    int save(Module module);
    int delete(Module module);
    int update(Module module);
    Module findById(String id);
    List<Module> findAll();
    List<Map> findAuthorDataByRoleId(@Param("roleId")String roleId);
    List<Module> findModuleByUserId(@Param("userId") String userId);
}
