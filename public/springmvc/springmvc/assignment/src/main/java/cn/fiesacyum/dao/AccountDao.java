package cn.fiesacyum.dao;

import cn.fiesacyum.pojo.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AccountDao {
    @Select("select * from account where name=#{name} and password=#{password}")
    Account findByNameAndPassword(Account account);

    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account (name,password) values (#{name},#{password})")
    void save(Account account);

    @Update("update account set password=#{password} where name=#{name}")
    void update(Account account);

    @Delete("delete from account where name=#{name}")
    void delete(Account account);
}
