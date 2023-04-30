package cn.tanhua.server.service;

import cn.tanhua.commons.utils.Constants;
import cn.tanhua.model.vo.HuanXinUserVo;
import cn.tanhua.server.interceptor.UserHolder;
import org.checkerframework.checker.units.qual.C;
import org.omg.CORBA.UnknownUserExceptionHolder;
import org.springframework.stereotype.Service;

@Service
public class HuanXinService {



    /**
     * 查询当前用户的环信账号
     * 根据规则hx+userId进行拼接,返回用户信息,用于使用账号信息进行登录
     */
    public HuanXinUserVo getHuanXinUser() {
        HuanXinUserVo huanXinUserVo = new HuanXinUserVo();
        huanXinUserVo.setUsername(Constants.HX_USER_PREFIX + UserHolder.getUserId());
        huanXinUserVo.setPassword(Constants.INIT_PASSWORD);
        return huanXinUserVo;
    }
}
