package com.cpc.multidbtx.mapper;

import com.cpc.multidbtx.entity.AccountInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.cpc.multidbtx.config.DataSource;


@Repository
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

    //更新账户金额
    @Update("update account_info set account_balance = account_balance + #{amount} where account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Select("select * from account_info where account_no=#{accountNo}")
    AccountInfo selectByNo(@Param("accountNo") String accountNo);

    @Select("select * from account_info")
    Page<AccountInfo> selectPage();

    @Insert("insert into account_info (id,account_name,account_no,account_password,account_balance) " +
            "values (NULL,#{accountName},#{accountNo},#{accountPassword},#{accountBalance})")
    @DataSource("default")
    void insert1(AccountInfo accountInfo);


    @Insert("insert into account_info (id,account_name,account_no,account_password,account_balance) " +
            "values (NULL,#{accountName},#{accountNo},#{accountPassword},#{accountBalance})")
    @DataSource("platform")
    void insert2(AccountInfo accountInfo);

    @Insert("insert into account_info (id,account_name,account_no,account_password,account_balance) " +
            "values (NULL,#{accountName},#{accountNo},#{accountPassword},#{accountBalance})")
    void insert3(AccountInfo accountInfo);
}
