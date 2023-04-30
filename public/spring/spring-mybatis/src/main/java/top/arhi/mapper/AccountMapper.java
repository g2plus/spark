package top.arhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.arhi.domain.Account;

import java.util.List;


public interface AccountMapper {
    List<Account> findAll();
}
