package top.arhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.arhi.domain.User;

@Mapper
public interface UserMapper extends MyBaseMapper<User> {

    User findById(Long id);

}
