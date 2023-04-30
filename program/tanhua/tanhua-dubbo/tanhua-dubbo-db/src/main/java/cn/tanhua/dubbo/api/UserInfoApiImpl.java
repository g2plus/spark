package cn.tanhua.dubbo.api;

import cn.hutool.core.collection.CollUtil;
import cn.tanhua.dubbo.mapper.UserInfoMapper;
import cn.tanhua.model.dto.RecommendUserDto;
import cn.tanhua.model.pojo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@DubboService
public class UserInfoApiImpl implements UserInfoApi{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Long save(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
        return userInfo.getId();
    }

    @Override
    public UserInfo findById(Long userId) {
        return userInfoMapper.selectById(userId);
    }

    @Override
    public void update(UserInfo userInfo){
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public Map<Long, UserInfo> findByIds(List<Long> ids, UserInfo userInfo) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper();
        //1.mybatisplus的范围查询
        wrapper.in("id",ids);
        //2.前端页面的筛选条件控制
        //wrapper的查询支持链式编程，默认为与查询
        if(userInfo!=null){
            if(userInfo.getAge()!=null){
                wrapper.lt("age",userInfo.getAge());
            }
            if(!StringUtils.isEmpty(userInfo.getGender())){
                wrapper.eq("gender",userInfo.getGender());
            }
        }
        //3.返回结果
        List<UserInfo> list = userInfoMapper.selectList(wrapper);
        Map<Long, UserInfo> map = CollUtil.fieldValueMap(list, "id");
        return map;

    }

}
