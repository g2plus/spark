package top.arhi.service.impl;

import top.arhi.dao.AccountDao;
import top.arhi.domain.Account;
import top.arhi.service.AccountService;
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
