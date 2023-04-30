package cn.fiesacyum.service;

import cn.fiesacyum.pojo.Account;

import java.util.List;

public interface AccountService {
    boolean verify(Account account);
    List<Account> findAll();
    void save(Account account);
    void update(Account account);
    void delete(Account account);
}
