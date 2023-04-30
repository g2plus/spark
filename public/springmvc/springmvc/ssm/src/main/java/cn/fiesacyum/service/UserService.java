package cn.fiesacyum.service;

import cn.fiesacyum.domain.User;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {
    @Transactional(readOnly = false)
    void save(User user);
    @Transactional(readOnly = false)
    void delete(String uuid);
    @Transactional(readOnly = false)
    void update(User user);

    PageInfo<User> findAll(int pageNum,int pageSize);

    User get(String uuid);

    User login(String username,String password);
}
