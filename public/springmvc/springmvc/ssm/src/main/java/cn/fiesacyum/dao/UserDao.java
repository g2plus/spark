package cn.fiesacyum.dao;

import cn.fiesacyum.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Insert("insert into user(uuid,username,password,realName,gender,birthday) values(#{uuid},#{username},#{password},#{realName},#{gender},#{birthday})")
    int save(User user);

    @Delete("delete from user where uuid=#{uuid}")
    int delete(String uuid);

    @Update("update user set username=#{username},password=#{password},realName=#{realName},gender=#{gender},birthday=#{birthday} where uuid=#{uuid}")
    int update(User user);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where uuid=#{uuid}")
    User get(String uuid);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password")String password);
}
