package cn.fiesacyum.service.impl;

import cn.fiesacyum.dao.AccountDao;
import cn.fiesacyum.pojo.Account;
import cn.fiesacyum.service.AccountService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public boolean find(String username, String password) {
        Account account = accountDao.findByUserNameAndPassword(username, password);
        if(account!=null){
            return true;
        }
        return false;
    }
}
