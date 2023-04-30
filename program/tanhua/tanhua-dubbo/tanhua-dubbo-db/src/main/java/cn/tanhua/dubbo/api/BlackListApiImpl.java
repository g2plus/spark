package cn.tanhua.dubbo.api;

import cn.tanhua.dubbo.mapper.BlackListMapper;
import cn.tanhua.dubbo.mapper.UserInfoMapper;
import cn.tanhua.model.pojo.BlackList;
import cn.tanhua.model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BlackListApiImpl implements BlackListApi {

    @Autowired
    private BlackListMapper blackListMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public IPage<UserInfo> getBlackList(Long userId, int page, int pagesize) {
        //创建一个page对象
        Page pages = new Page(page, pagesize);
        //传入pages参数,使用@Param("pages")返回一个Ipage对象。
        IPage<UserInfo> iPage = userInfoMapper.findByUserId(pages, userId);
        return iPage;
    }

    @Override
    public void reomoveFromBlackList(Long userId, Long uid) {
        QueryWrapper<BlackList> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId)
               .eq("black_user_id",uid);//and查询

        blackListMapper.delete(wrapper);
    }
}
