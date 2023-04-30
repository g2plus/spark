package cn.tanhua.dubbo.mapper;

import cn.tanhua.model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM `tb_user` WHERE mobile REGEXP '[0-9]{11}'")
    List<User> finAllUser();
}
