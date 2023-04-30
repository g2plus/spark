package com.heima.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.model.user.pojos.ApUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface ApUserMapper extends BaseMapper<ApUser> {

    /**
     * 根据手机号寻找用户
     * @param phone
     * @return
     */

    ApUser findByPhone(@Param("phone")String phone);
}
