package cn.fiesacyum.service.system;

import cn.fiesacyum.domain.system.Module;
import cn.fiesacyum.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
     void save(User user);
     void update(User user);
     void delete(User user);
     User findById(String id);
     List<User> findAll();
     PageInfo findAll(int page, int size);
     void updateRole(String userId, String[] roleIds);
     User login(String email,String password);

     /**
      * 根据userid调用ModuleDao的方法，返回module列表
      * @param id
      * @return
      */
     List<Module> findModuleById(String id);
}
