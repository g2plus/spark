package cn.fiesacyum.dao;

import cn.fiesacyum.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {
    @Select("Select * from account where name=#{name} and password=#{password}")
    User findAccountByNameAndPassword(User user);
}
