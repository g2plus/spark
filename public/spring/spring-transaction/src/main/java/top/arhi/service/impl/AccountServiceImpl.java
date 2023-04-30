package top.arhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arhi.mapper.AccountMapper2;
import top.arhi.service.AccountService;
import top.arhi.service.LogService;
import top.arhi.util.DateUtil;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper2 accountMapper2;

    @Autowired
    private LogService logService;



    @Override
    public void transfter(String inName, String outName, Integer money)  {
        Boolean flag = false;
        try {
            accountMapper2.inMoney(inName,money);
//            int i=1/0;
            accountMapper2.outMoney(outName,money);
            flag=true;
        } finally {
            //tinyint与Boolean的对应关系，true->1,false->0;
           logService.logInfo(outName,inName,money, DateUtil.formatDate(),flag);
        }
    }
}
