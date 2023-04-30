package cn.fiesacyum.dao;

import cn.fiesacyum.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    @Insert("INSERT INTO user VALUES(null,#{username},#{password})")
    public Integer add(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    public Integer delete(Integer id);

    @Update("UPDATE user SET username=#{username},password=#{password} WHERE id=#{id}")
    public Integer update(User user);

    @Select("SELECT * FROM user")
    public List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User findById(Integer id);

}
