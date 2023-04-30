package cn.tanhua.dubbo.api;

import cn.tanhua.model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;


public interface BlackListApi {


    IPage<UserInfo> getBlackList(Long userId, int page, int pagesize);

    void reomoveFromBlackList(Long userId, Long uid);
}
