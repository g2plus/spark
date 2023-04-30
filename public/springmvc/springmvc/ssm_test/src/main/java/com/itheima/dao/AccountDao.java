package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountDao {
    @Select("select * from account where username=#{username} and password=#{password}")
    Account findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
