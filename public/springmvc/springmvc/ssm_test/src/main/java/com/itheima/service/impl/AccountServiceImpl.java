package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Override
    public boolean find(String username, String password) {
        Account account = accountDao.findByUserNameAndPassword(username, password);
        if(account!=null){
            return true;
        }
        return false;
    }
}
