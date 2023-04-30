package top.arhi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import top.arhi.pojo.User;


/**
 * @author e2607
 */
public interface UserMapper extends BaseMapper<User> {
    User findById(Long id);
}
