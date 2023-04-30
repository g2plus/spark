package com.cpc.multidbtx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpc.multidbtx.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
