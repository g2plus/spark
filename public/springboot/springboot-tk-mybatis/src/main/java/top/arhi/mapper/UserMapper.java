package top.arhi.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import top.arhi.domain.User;

/**
 * 基础通用mapper
 */
public interface UserMapper extends Mapper<User> {
    User findById(Long id);
    User selectById(Long id);
}