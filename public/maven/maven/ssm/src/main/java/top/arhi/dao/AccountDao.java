package top.arhi.dao;

import org.springframework.stereotype.Repository;
import top.arhi.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Repository
public interface AccountDao {
    @Select("select username,password from account where username=#{username} and password=#{password}")
    Account findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
