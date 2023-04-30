package cn.tanhua.dubbo.api;

import cn.tanhua.dubbo.mapper.SettingsMapper;
import cn.tanhua.model.pojo.Settings;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class SettingsApiImpl implements SettingsApi{

    @Autowired
    private SettingsMapper settingsMapper;


    @Override
    public Settings findByUserId(Long userId) {
        QueryWrapper<Settings> wrapper = new QueryWrapper();
        wrapper.eq("user_id",userId);
        return settingsMapper.selectOne(wrapper);
    }

    @Override
    public void saveNotificationSetting(Settings settings) {
        settingsMapper.insert(settings);
    }

    @Override
    public void updateNotificationSetting(Settings settings) {
        settingsMapper.updateById(settings);
    }
}
