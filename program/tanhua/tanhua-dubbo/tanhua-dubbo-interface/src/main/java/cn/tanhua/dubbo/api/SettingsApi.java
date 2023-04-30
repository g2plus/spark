package cn.tanhua.dubbo.api;

import cn.tanhua.model.pojo.Settings;

public interface SettingsApi {

    Settings findByUserId(Long userId);

    void saveNotificationSetting(Settings settings);

    void updateNotificationSetting(Settings settings);
}
