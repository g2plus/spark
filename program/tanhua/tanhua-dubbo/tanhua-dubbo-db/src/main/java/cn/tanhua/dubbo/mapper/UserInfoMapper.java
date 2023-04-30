package cn.tanhua.dubbo.mapper;

import cn.tanhua.model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select tb_user_info.* from tb_user_info where id in" +
            " (select black_user_id from tb_black_list where user_id = #{userId});")
    IPage<UserInfo> findByUserId(@Param("pages")Page pages, @Param("userId")Long userId);
}
