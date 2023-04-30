package cn.itcast.localtx.service.impl;

import cn.itcast.localtx.dao.AccountInfoDao;
import cn.itcast.localtx.service.AccountInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
@Slf4j
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    //备注使用try finally 不加catche（Exception e）TransactionAspect...代码时，程序不会回滚.
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean updateAccountBalance() {
        Boolean flag=false;
        try{
            accountInfoDao.updateAccountBalance("1", -100.00);
            accountInfoDao.updateAccountBalance("2", 100.00);
            int i = 1 / 0;
            flag=true;
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally{
            return flag;
        }
    }
}
