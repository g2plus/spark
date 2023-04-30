package cn.fiesacyum.service.impl;

import cn.fiesacyum.dao.AccountDao;
import cn.fiesacyum.pojo.Account;
import cn.fiesacyum.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    public boolean verify(Account account) {
        Account tempAccount = accountDao.findByNameAndPassword(account);
        if(tempAccount!=null){
            return true;
        }
        return false;
    }

    public List<Account> findAll() {
        List<Account> list = accountDao.findAll();
        return list;
    }

    public void save(Account account) {
        accountDao.save(account);
    }

    public void update(Account account){
        accountDao.update(account);
    }

    public void delete(Account account) {
        accountDao.delete(account);
    }
}
